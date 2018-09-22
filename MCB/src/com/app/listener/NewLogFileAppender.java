package com.app.listener;


import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Calendar;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.helpers.CountingQuietWriter;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.LoggingEvent;

/**
 * <p> Description: 日志实现类</p>
 * <p> Copyright: Copyright (c) 2014 </p>
 * <p> Create Date: 2014-04-17 </p>
 * <p> Company: </p> 
 * @author wangpengpeng
 * @version $Id: NewLogFileAppender.java,v 1.0 wangpengpeng Exp $
 */
public class NewLogFileAppender extends FileAppender {

	/**
	 * 文件最大值
	 */
	protected long maxFileSize;

	/**
	 * 序数
	 */
	protected int maxBackupIndex;

	/**
	 * 日志创建日期.
	 */
	private String logCreateDate = null;

	public NewLogFileAppender() {
		super();
		maxFileSize = 10485760L;
		maxBackupIndex = 1;
	}

	public NewLogFileAppender(Layout layout, String filename, boolean append)
			throws IOException {
		super(layout, filename, append);
		maxFileSize = 10485760L;
		maxBackupIndex = 1;
	}

	public NewLogFileAppender(Layout layout, String filename)
			throws IOException {
		super(layout, filename);
		maxFileSize = 10485760L;
		maxBackupIndex = 1;
	}

	public int getMaxBackupIndex() {
		return maxBackupIndex;
	}

	public long getMaximumFileSize() {
		return maxFileSize;
	}

	/**
	 * 重命名日志文件
	 */
	public void rollOver() {
		LogLog.debug("rolling over count="
				+ ((CountingQuietWriter) super.qw).getCount());
		LogLog.debug("maxBackupIndex=" + maxBackupIndex);
		// 重命名所有老的日志文件
		if (maxBackupIndex > 0) {
			File file = new File(super.fileName + '.' + logCreateDate + '.'
					+ maxBackupIndex);
			if (file.exists()) {
				file.delete();
			}
			File target;
			for (int i = maxBackupIndex - 1; i >= 1; i--) {
				file = new File(super.fileName + '.' + logCreateDate + "." + i);
				if (file.exists()) {
					target = new File(super.fileName + '.' + logCreateDate
							+ '.' + (i + 1));
					LogLog.debug("Renaming file " + file + " to " + target);
					file.renameTo(target);
				}
			}

			target = new File(super.fileName + '.' + logCreateDate + "." + 1);
			closeFile();
			file = new File(super.fileName);
			LogLog.debug("Renaming file " + file + " to " + target);
			file.renameTo(target);
		}
		// 重新命名新的日志文件
		try {
			setFile(super.fileName, false, super.bufferedIO, super.bufferSize);
		} catch (IOException e) {
			LogLog.error("setFile(" + super.fileName + ", false) call failed.",
					e);
		}
	}

	/**
	 * 自定义日志文件名称
	 */
	public synchronized void setFile(String file) {
		logCreateDate = nowDate();
		String logFile = file.replaceAll("YYYYMMDD", logCreateDate);
		int idx = logFile.lastIndexOf("/");
		if (-1 != idx) {
			File filePath = new File(logFile.substring(0, idx));
			if (!filePath.exists())
				filePath.mkdirs();
		}
		super.setFile(logFile);
	}

	/**
	 * 给日志文件命名
	 */
	public synchronized void setFile(String fileName, boolean append,
			boolean bufferedIO, int bufferSize) throws IOException {
		super.setFile(fileName, append, super.bufferedIO, super.bufferSize);
		if (append) {
			File f = new File(fileName);
			((CountingQuietWriter) super.qw).setCount(f.length());
		}
	}

	public void setMaxBackupIndex(int maxBackups) {
		maxBackupIndex = maxBackups;
	}

	public void setMaximumFileSize(long maxFileSize) {
		this.maxFileSize = maxFileSize;
	}

	public void setMaxFileSize(String value) {
		maxFileSize = OptionConverter.toFileSize(value, maxFileSize + 1L);
	}

	protected void setQWForFiles(Writer writer) {
		super.qw = new CountingQuietWriter(writer, super.errorHandler);
	}

	/**
	 * 记录日志文件的入口方法
	 */
	protected void subAppend(LoggingEvent event) {
		// 判断日期是否一致
		// 如果不一致, 则创建新日志文件
		String date = nowDate();
		if (logCreateDate.equals(date)) {
			// 不做处理
		} else {
			// 切换文件
			switchFileName(date);
		}

		super.subAppend(event);
		if (super.fileName != null
				&& ((CountingQuietWriter) super.qw).getCount() >= maxFileSize) {
			rollOver();
		}
	}

	/**
	 * 切换日志文件
	 * 
	 * @param date
	 */
	private synchronized void switchFileName(String date) {
		closeFile();
		String logFile = super.fileName.replaceAll(logCreateDate, date);
		int idx = logFile.lastIndexOf("/");
		if (-1 != idx) {
			File filePath = new File(logFile.substring(0, idx));
			if (!filePath.exists())
				filePath.mkdirs();
		}
		logCreateDate = date;

		try {
			setFile(logFile, false, super.bufferedIO, super.bufferSize);
		} catch (IOException e) {
			LogLog.error("setFile(" + logFile + ", false) call failed.", e);
		}
	}

	/**
	 * 获得当前日期的八位字符串
	 * 
	 * @return
	 */
	private String nowDate() {
		Calendar now = Calendar.getInstance();

		String year, month, day;
		year = String.valueOf(now.get(Calendar.YEAR));
		if (now.get(Calendar.MONTH) < 9) {
			month = "0" + String.valueOf(now.get(Calendar.MONTH) + 1);
		} else {
			month = String.valueOf(now.get(Calendar.MONTH) + 1);
		}
		if (now.get(Calendar.DAY_OF_MONTH) < 10) {
			day = "0" + String.valueOf(now.get(Calendar.DAY_OF_MONTH));
		} else {
			day = String.valueOf(now.get(Calendar.DAY_OF_MONTH));
		}
		return (year + month + day);
	}
}
