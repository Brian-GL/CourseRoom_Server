/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.colecciones;

import datos.estructuras.Nodo;


/**
 *
 * @author LENOVO
 * @param <T> The Collection Data Type
 */
public class Coleccion<T>{
    protected Lista<T> _coleccion;
    
    public Coleccion(){
        _coleccion = new Lista<>();
    }
    
    public Coleccion(Lista<T> lista_Doblemente_Ligada){
        _coleccion.join_back(lista_Doblemente_Ligada);
    }

    public Lista list(){
        return  this._coleccion;
    }
    
    public boolean is_empty() {
        return _coleccion.is_empty();
    }

    public int size() {
        return _coleccion.size();
    }

    public T first() throws NullPointerException {
        return _coleccion.first();
    }

    public T last() throws NullPointerException {
        return _coleccion.last();
    }

    public T medium() throws NullPointerException {
        return _coleccion.medium();
    }

    public Nodo<T> front() throws NullPointerException {
        return _coleccion.front();
    }

    public Nodo<T> back() throws NullPointerException {
        return _coleccion.back();
    }

    public Nodo<T> middle() throws NullPointerException {
        return _coleccion.middle();
    }

    public void print() {
        _coleccion.print();
    }

    public boolean contains(T element) {
        return _coleccion.contains(element);
    }

    public T get(int index) throws NullPointerException {
        return _coleccion.get(index);
    }

    public int position(T element) {
        return _coleccion.position(element);
    }

    public int node_position(Nodo node) {
        return _coleccion.node_position(node);
    }

    public void clear() {
        _coleccion.clear();
    }

    public void erase_at(int position) {
        _coleccion.erase_at(position);
    }

    public void remove(T element) {
        _coleccion.remove(element);
    }

    public Nodo<T> node(T element) throws NullPointerException {
        return _coleccion.node(element);
    }

    public Nodo<T> node(int index) throws NullPointerException {
        return _coleccion.node(index);
    }

    @Override
    public String toString() throws NullPointerException {
        return _coleccion.toString();
    }

    @Override
    public int hashCode() {
        return _coleccion.hashCode();
    }

    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(Object obj) {
        return _coleccion.equals(obj);
    }
    
    public void dispose(){
        this._coleccion.clear();
        this._coleccion = null;
    }
      
    
}
