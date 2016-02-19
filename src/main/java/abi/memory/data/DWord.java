package abi.memory.data;

import abi.memory.address.Address;
import abi.memory.address.Address32;
import util.B;

import java.nio.ByteOrder;

/**
 * Created by jamesrichardson on 2/11/16.
 */
public class DWord extends Data {
    public static final Word SIZEOF_B = new Word("0x0004",ByteOrder.BIG_ENDIAN);
    public static final Word SIZEOF_L = new Word("0x0400",ByteOrder.LITTLE_ENDIAN);
    public static final DWord NULL = new DWord();

    public DWord(){
        super(0, Address32.NULL, Address32.NULL, ByteOrder.BIG_ENDIAN);
    }

    public DWord(byte[] in,ByteOrder order){
        this(in,new Address32("0x00000000"),order);
    }

    public DWord(byte zero,byte one,byte two,byte three,ByteOrder order){
        this(zero,one,two,three,new Address32("0x00000000"),order);
    }

    public DWord(String in,ByteOrder order){
        this(in,new Address32("0x00000000"),order);
    }

    public DWord(byte[] in,Address beginAddress,ByteOrder order){
        super(4,beginAddress,beginAddress.clone(),order);
        this.endAddress.add(new Address32("0x00000004"));
        if(in.length != BYTES){
            return;
        }
        for(int i=0;i<BYTES;i++){
            container[i]=in[i];
        }
    }

    public DWord(byte zero,byte one,byte two,byte three,Address beginAddress,ByteOrder order){
        super(4,beginAddress,beginAddress.clone(),order);
        this.endAddress.add(new Address32("0x00000004"));
        container[0]=zero;
        container[1]=one;
        container[2]=two;
        container[3]=three;
    }

    public DWord(String in,Address beginAddress,ByteOrder order){
        super(4,beginAddress,beginAddress.clone(),order);
        this.endAddress.add(new Address32("0x00000004"));
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

        return new DWord(flip,BYTEORDER==ByteOrder.BIG_ENDIAN?ByteOrder.LITTLE_ENDIAN:ByteOrder.BIG_ENDIAN);
    }

    @Override
    public DWord clone() {
        return new DWord(container,BYTEORDER);
    }
}
