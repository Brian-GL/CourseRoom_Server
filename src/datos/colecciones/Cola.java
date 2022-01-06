/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.colecciones;

/**
 *
 * @author LENOVO
 */
public class Cola<T> extends Coleccion<T> {

    public Cola() {
        super();
    }

    public Cola(Lista<T> doublyLinkedList) {
        super(doublyLinkedList);
    }
    
    public void enqueue(T element){ 
        this._coleccion.push_back(element);
    }

    public boolean line_up(T element){ 
        return this._coleccion.add_back(element);
    }

    public T dequeue() throws NullPointerException{
        return this._coleccion.delist();
    }
    
    
}
