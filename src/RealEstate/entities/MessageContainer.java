package RealEstate.entities;

import RealEstate.exceptions.MessageNotFoundException;

import java.util.HashMap;

public class MessageContainer<Integer, Message> extends HashMap<java.lang.Integer, RealEstate.entities.Message> {
    @Override
    public RealEstate.entities.Message get(Object key) {
        if (this.containsKey(key)) {
            return super.get(key);
        }
        else{
            throw new MessageNotFoundException();
        }
    }

    @Override
    public String toString() {
        StringBuilder returnedString = new StringBuilder(" ");
        for (RealEstate.entities.Message message : this.values()){
            returnedString.append(message.toString()).append("\n");
        }
        return returnedString.toString();
    }

}
