/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.estructuras;

import java.util.Objects;

/**
 *
 * @author LENOVO
 */
public class Nodo_Par<A,B> implements Comparable<Nodo_Par<A,B>>{
    
    private Nodo_Par<A,B> _previous;
    private Par<A,B> _element;
    private Nodo_Par<A,B> _next;

    public Nodo_Par(Nodo_Par<A,B> previous, A first_element, B second_element, Nodo_Par<A,B> next) {
        this._previous = previous;
        this._element = new Par<>(first_element,second_element);
        this._next = next;
    }

    public Nodo_Par(A first_element, B second_element) {
        this._element = new Par<>(first_element,second_element);
    }
    
    /**
     * @return the _previous
     */
    public Nodo_Par<A,B> previous() {
        return _previous;
    }

    /**
     * @param _previous the _previous to set
     */
    public void previous(Nodo_Par<A,B> _previous) {
        this._previous = _previous;
    }

    /**
     * @return the first
     */
    public A first() throws NullPointerException{
        return _element.first();
    }

    /**
     * @param first the first to set
     */
    public void first(A first) {
        this._element.first(first);
    }

    /**
     * @return the second
     */
    public B second() throws NullPointerException{
        return _element.second();
    }

    /**
     * @param second the second to set
     */
    public void second(B second) {
        this._element.second(second);
    }
    
    /**
     * @return the _value
     */
    public Par<A,B> element() {
        return _element;
    }

    public void element(A first, B second) {
        this.first(first);
        this.second(second);
    }
    
    public void element(Par<A,B> element) {
        this.first(element.first());
        this.second(element.second());
    }

    /**
     * @return the _next
     */
    public Nodo_Par<A,B> next() {
        return _next;
    }

    /**
     * @param _next the _next to set
     */
    public void next(Nodo_Par<A,B> _next) {
        this._next = _next;
    }

    public boolean has_next() {
        return this.next() != null;
    }
    
    public boolean has_previous() {
        return this.previous() != null;
    }
    
     public Par<A,B> next_element() {
       return (has_next()) ? this.next().element() : null;
    }
    
    public Par<A,B> previous_element(){
        return (has_previous()) ? this.previous().element() : null;
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        super.clone();
        return new Nodo_Par<>(this.previous(),this.first(),this.second(),this.next());
    }


    @Override
    public String toString() throws NullPointerException{
       String toString = "";
       
        if(has_previous()){
            toString = this.previous() + "\n";
        }
        
        toString = this.element()+ "\n";
        
        if(has_next()){
            toString = this.next() + "\n";
        }
      
        return toString;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        if(has_previous()){
            hash = 97 * hash + Objects.hashCode(this.previous());
        }
        if(has_next()){
            hash = 97 * hash + Objects.hashCode(this.previous());
        }
        hash = 97 * hash + element().hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) 
            return false;
        
        if (this == obj) 
            return true;

        if (!(obj instanceof Nodo_Par<?,?>)) 
            return false; 
        
         Nodo_Par<A,B> other = (Nodo_Par<A,B>) obj;
         
         if(has_next() && has_previous()
                 && other.has_next() && other.has_previous()){
             
             return this.next().equals(other.next())
                     && this.previous().equals(other.previous())
                     && this.element().equals(other.element());
             
         }
         else if(!has_next() && has_previous()
                 && !other.has_next() && other.has_previous()){
             return this.previous().equals(other.previous())
                     && this.element().equals(other.element());
         }
         else if(has_next() && !has_previous()
                 && other.has_next() && !other.has_previous()){
             return this.next().equals(other.next())
                     && this.element().equals(other.element());
         }
         else if(!has_next() && !has_previous()
                 && !other.has_next() && !other.has_previous()){
             return this.element().equals(other.element());
         }
      
         return false;
         
    }

    
    @Override
    public int compareTo(Nodo_Par<A, B> o) {
        return  this._element.compareTo(o._element);
    }
    
    public void dispose(){
        this.next(null);
        this.previous(null);
        this._element.dispose();
        this._element = null;
    }
}
