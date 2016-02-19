package abi.memory.data;

import abi.memory.Container;
import abi.memory.address.Address;
import abi.memory.address.Address32;
import util.B;

import java.nio.ByteOrder;

/**
 * Created by jamesrichardson on 2/16/16.
 */
public class DataRange extends Data{

    public DataRange(){
        this(new byte[0],null,null,ByteOrder.BIG_ENDIAN);
    }

    public DataRange(byte[] in, Address begin, Address end, ByteOrder order) {
        super(in.length, begin,end, order);
        for(int i=0;i<in.length;i++){
            container[i]=in[i];
        }
    }

    @Override
    public Container flipByteOrder() {
        return new DataRange(B.flipByteOrder(container),this.beginAddress,this.endAddress,this.BYTEORDER);
    }

    public void setDataType(Type in){
        this.type=in;
    }

    @Override
    public Type getDataType() {
        return type;
    }

    @Override
    public Data clone() {
        return new DataRange(container,this.beginAddress,this.endAddress,this.BYTEORDER);
    }
}
