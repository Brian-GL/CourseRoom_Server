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
public class Pila<T> extends Coleccion<T>{

    public Pila() {
        super();
    }

    public Pila(Lista<T> lista) {
        super(lista);
    }
    
    public void Apilar(T elemento){ 
        this._coleccion.push_front(elemento);
    }

    public boolean Enpilar(T elemento){
        return this._coleccion.add_front(elemento);
    }

    public T Desapilar() throws NullPointerException{
        return this._coleccion.unlist();
    }
    
}
