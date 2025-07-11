package com.java.sample.blockchain;

import java.util.Date;

//https://github.com/Xunzhuo/Baby-BlockChain?tab=readme-ov-file

public class Block {
    public String hash;
    public String previousHash;
    private String data ;
    private long timeStamp;
    private int nonce;

    //Block constructor
    public Block(String data,String previousHash ) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash() {
        return StringUtil.applySha256(
                previousHash +
                        Long.toString(timeStamp) +
                        data
        );

    }
    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0"
        while(!hash.substring( 0, difficulty).equals(target)) {
            nonce ++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }


}
