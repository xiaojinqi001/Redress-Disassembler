package abi.mach;

import abi.generic.abi.*;

import java.util.LinkedList;

/**
 * Created by jamesrichardson on 2/10/16.
 */
public class MachO32 extends ABI implements Mach {

    private static final ABIType ABI_TYPE = ABIType.MACH_32;
    private LinkedList<Command> commands = new LinkedList<>();
    private LinkedList<Segment> segments = new LinkedList<>();
    private LinkedList<Section> sections = new LinkedList<>();

    private Loader.mach_header mach_header = new Loader.mach_header();

    public MachO32(byte[] binary){
        super(binary);
    }

    @Override
    public byte[] getRaw() {
        return raw;
    }

    @Override
    public Header getHeader() {
        return mach_header;
    }

    @Override
    public ABIType getArch() {
        return ABI_TYPE;
    }

    @Override
    public LinkedList<Command> getCommands() {
        return commands;
    }

    @Override
    public LinkedList<Segment> getSegments() {
        return segments;
    }

    @Override
    public LinkedList<Section> getSections() {
        return sections;
    }
}