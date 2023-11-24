package oop23_1010.utils;

import oop23_1010.types.BlockType;

public class Randomizer {

    public static BlockType getRandomPuzzle(){
        Integer index = 0;

        Double temp = Math.floor(Math.random() * (BlockType.values().length - 1));
        Integer rand = temp.intValue();

        for (BlockType block : BlockType.values()) {
            if(index == rand){
                return block;
            }
            index++;
        }
        return null;
    }    
}
