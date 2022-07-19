package RealEstate.entities;

import RealEstate.exceptions.MessageNotFoundException;

public class MessageContainer<Integer, Message> extends Container<Integer, Message> {
    /**
     * @param key the key whose associated value is to be returned
     * @return the value associated with key
     * @throws MessageNotFoundException if no Message is associated with key in the hashmap
     */
    @Override
    public Message get(Object key) throws MessageNotFoundException {
        try {
            return super.get(key);
        } catch(IllegalArgumentException e) {
            throw new MessageNotFoundException();
        }
    }
}
