/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.estructuras;

import java.util.Objects;

public class Par<A,B> implements Comparable<Par<A,B>>{
    
    private A _first_element;
    private B _second_element;
    private Comparing<A> _first_comparing;
    private Comparing<B> _second_comparing;
    
    public Par(A first, B second){
        this._first_element = first;
        this._second_element = second;
        _first_comparing = new Comparing<>();
        _second_comparing = new Comparing<>();
    }
    
    public Par(Par<A,B> doublet){
        
        if(doublet != null){
            this._first_element = doublet.first();
            this._second_element = doublet.second();
        }
        
        _first_comparing = new Comparing<>();
        _second_comparing = new Comparing<>();
    }

    /**
     * @return the first
     */
    public A first() throws NullPointerException{
        return _first_element;
    }

    /**
     * @param first the first to set
     */
    public void first(A first) {
        this._first_element = first;
    }

    /**
     * @return the second
     */
    public B second() throws NullPointerException{
        return _second_element;
    }

    /**
     * @param second the second to set
     */
    public void second(B second) {
        this._second_element = second;
    }

    @Override
    public String toString() throws NullPointerException{
        return this._first_element + "\n" + this._second_element;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this._first_element);
        hash = 41 * hash + Objects.hashCode(this._second_element);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) 
            return false;
        
        if (this == obj) 
            return true;

        if (!(obj instanceof Par<?,?>)) 
            return false; 
        
        Par<A, B> other = (Par<A, B>) obj;
        
        return this._first_element.equals(other.first()) && this._second_element.equals(other.second());
            
    }
    
    public boolean equals(A first_element,B second_element) {
        return this._first_element.equals(first_element) && this._second_element.equals(second_element);
            
    }

    public int compare_first(Par<A,B> doublet){
        return (doublet != null) ?  _first_comparing.compare(this._first_element, doublet.first()) : 1;
    }
    
    public int conpare_second(Par<A,B> doublet){
        return (doublet != null) ?  _second_comparing.compare(this._second_element, doublet.second()) : 1;
    }
    
    public int compare_first(A value){
        return _first_comparing.compare(this._first_element, value);
    }
    
    public int conpare_second(B value){
        return  _second_comparing.compare(this._second_element, value);
    }

    @Override
    public int compareTo(Par<A, B> o) {
        return  compare_first(o.first()) + conpare_second(o.second());
    }
    
    public void dispose(){
        this._first_comparing = null;
        this._second_comparing = null;
        this._first_element = null;
        this._second_element = null;
    
    }

}
