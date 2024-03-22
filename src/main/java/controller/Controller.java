package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

public interface Controller {
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws SerialException, IOException;

}
