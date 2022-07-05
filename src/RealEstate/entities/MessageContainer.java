package RealEstate.entities;

import RealEstate.exceptions.MessageNotFoundException;

import java.util.HashMap;

public class MessageContainer<Integer, Message> extends HashMap<Integer, Message> {
    @Override
    public Message get(Object key) {
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
        for (Message message : this.values()){
            returnedString.append(message.toString());
        }
        return returnedString.toString();
    }

}
