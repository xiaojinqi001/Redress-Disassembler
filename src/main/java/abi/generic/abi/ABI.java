package abi.generic.abi;

import abi.generic.memory.data.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * Created by jamesrichardson on 2/10/16.
 */
public abstract class ABI {

    protected final byte[] raw;

    public ABI(byte[] binary){
        raw=binary;
    }

    public abstract ArrayList<Data> getProcessedData();
    public abstract byte[] getRaw();
    public abstract Header getHeader();
    public abstract ABIType getArch();
    public abstract LinkedList<Command> getCommands();
    public abstract LinkedList<Segment> getSegments();
    public abstract LinkedList<Section> getSections();

}
