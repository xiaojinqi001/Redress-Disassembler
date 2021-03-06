package redress.memory.data;

import redress.abi.generic.IContainer;
import redress.abi.generic.IStructure;
import redress.abi.generic.visitors.IVisitor;
import redress.memory.address.AbstractAddress;
import redress.memory.address.Address32;
import redress.util.B;

import java.nio.ByteOrder;
import java.util.LinkedList;

/**
 * Created by jamesrichardson on 2/11/16.
 */
public class DWord extends AbstractData {
    public static final Word SIZEOF_B = new Word("0x0004", AbstractData.Type.DATA_BYTE,ByteOrder.BIG_ENDIAN);
    public static final Word SIZEOF_L = new Word("0x0400", AbstractData.Type.DATA_BYTE,ByteOrder.LITTLE_ENDIAN);
    public static final DWord NULL = new DWord();

    public DWord(){
        super(0,null,Type.DATA_NULL ,ByteOrder.BIG_ENDIAN);
    }

    public DWord(byte[] in,Type type,ByteOrder order){
        this(in,Address32.NULL,null,type,order);
    }
    public DWord(byte[] in,AbstractAddress beginAddress,IStructure parent, Type type,ByteOrder order){
        super(4,parent,type,order);
        this.beginAddress=beginAddress;
        this.endAddress=beginAddress.clone();
        B.add(this.endAddress,new Address32("0x00000004"));
        if(in.length != BYTES){
            return;
        }
        for(int i=0;i<BYTES;i++){
            container[i]=in[i];
        }
    }

    public DWord(String in,Type type,ByteOrder order){
        this(in,Address32.NULL,null,type,order);
    }
    public DWord(String in,AbstractAddress beginAddress,IStructure parent,Type type,ByteOrder order){
        super(4,parent,type,order);
        this.beginAddress=beginAddress;
        this.endAddress=beginAddress.clone();
        B.add(this.endAddress,new Address32("0x00000004"));
        final byte[] tmp = B.stringToBytes(in);
        if(tmp.length != BYTES){
            System.out.println("Size Overflow!");
            return;
        }
        for(int i=0;i<BYTES;i++){
            container[i]=tmp[i];
        }
    }

    public void setDataType(Type in){
        this.type=in;
    }

    @Override
    public Type getDataType() {
        return type;
    }

    @Override
    public DWord flipByteOrder() {
        byte[] flip = new byte[BYTES];
        for(int i=0;i<BYTES;i++){
            flip[i] = container[(BYTES-1)-i];
        }

        return new DWord(flip,this.type,BYTEORDER==ByteOrder.BIG_ENDIAN?ByteOrder.LITTLE_ENDIAN:ByteOrder.BIG_ENDIAN);
    }

    @Override
    public DWord clone() {
        return new DWord(container,this.type,BYTEORDER);
    }


}
