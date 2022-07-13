package RealEstate.entities;

import RealEstate.exceptions.MessageNotFoundException;

public class MessageContainer<Integer, Message> extends Container<Integer, Message> {
    @Override
    public Message get(Object key) throws MessageNotFoundException {
        try {
            return super.get(key);
        } catch(IllegalArgumentException e) {
            throw new MessageNotFoundException();
        }
    }
}
