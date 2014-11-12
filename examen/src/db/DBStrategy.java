package db;

import java.io.File;
import java.util.ArrayList;

import domain.Winkel;

public interface DBStrategy {
	void Read(Winkel w,File file) throws DbException;
	void Write(Winkel w,File file) throws DbException;
}
