/*
 * Pair<A,B>o change this license header, choose License Headers in Project Properties.
 * Pair<A,B>o change this template file, choose Pair<A,B>ools | Pair<A,B>emplates
 * and open the template in the editor.
 */
package datos.colecciones;

import datos.estructuras.Par;
import datos.estructuras.Nodo_Par;

public class Lista_Pares<A,B>{

    private Nodo_Par<A,B> _front;
    private Nodo_Par<A,B> _middle;
    private Nodo_Par<A,B> _back;
    private int _size;

    //Public constructors
    public Lista_Pares() {
        _size = 0;
    }

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Lista_Pares(Lista_Pares<A,B> list) {
        this._size = 0;

        Nodo_Par<A,B> middle = list.middle();

        if (middle != null) {

            this.push_back(middle.element());

            Nodo_Par<A,B> go_down = middle.previous();
            Nodo_Par<A,B> go_up = middle.next();

            while (go_down != null || go_up != null) {

                if (go_down != null) {
                    push_front(go_down.element());
                    go_down = go_down.previous();
                }
                if (go_up != null) {
                    push_back(go_up.element());
                    go_up = go_up.next();
                }
            }
        }
    }
    
    //Public Methods
    
    public boolean is_empty() {
        return this._front == null;
    }


    public int size(){
        return this._size;
    }

    
    public Par<A,B> first() throws NullPointerException {
        return (this._front != null) ? this._front.element() : null;
    }

    
    public Par<A,B> last() throws NullPointerException {
        return (this._back != null) ? this._back.element() : null;
    }

    
    public Par<A,B> medium() throws NullPointerException {
        return (this._middle != null) ? this._middle.element() : null;
    }

    
    public Nodo_Par<A,B> front() throws NullPointerException {
        return this._front;
    }

    
    public Nodo_Par<A,B> back() throws NullPointerException {
        return this._back;
    }

    
    public Nodo_Par<A,B> middle() throws NullPointerException {
        return this._middle;
    }

    
    public Par<A,B> unenlist_front() throws NullPointerException {
        if (!is_empty()) {
            Par<A,B> auxiliar = this._front.element();
            pop_front();
            return auxiliar;
        }
        return null;
    }

    
    public Par<A,B> unenlist_back() throws NullPointerException {
        if (!is_empty()) {
            Par<A,B> auxiliar = this._back.element();
            pop_back();
            return auxiliar;
        }
        return null;
    }

   
    
    public void push_back(Par<A,B> ... elements) {
        Nodo_Par<A, B> node;
        for(Par<A,B> element : elements){
            this._size = this._size + 1;
            if (is_empty()) {
                node = new Nodo_Par<>(element.first(), element.second());
                this._front = this._back = this._middle = node;
            } else {
                node = new Nodo_Par<>(this._back, element.first(), element.second(), null);
                this._back.next(node);
                this._back = node;
                if (this._size % 2 != 0) {
                    this._middle = this._middle.next();
                }
            }
        }
    }
    
    public void push_back(A first_element, B second_element) {
        Nodo_Par<A, B> node;
        this._size = this._size + 1;
        if (is_empty()) {
            node = new Nodo_Par<>(first_element,second_element);
            this._front = this._back = this._middle = node;
        } else {
            node = new Nodo_Par<>(this._back, first_element,second_element, null);
            this._back.next(node);
            this._back = node;
            if (this._size % 2 != 0) {
                this._middle = this._middle.next();
            }
        }
    }

    public void push_front(Par<A,B> ... elements) {
        Nodo_Par<A, B> node;
        for(Par<A,B> element: elements){
            this._size = this._size + 1;
            if (is_empty()) {
                node = new Nodo_Par<>(element.first(), element.second());
                this._front = this._back = this._middle = node;
            } else {
                node = new Nodo_Par<>(null, element.first(), element.second(), this._front);
                this._front.previous(node);
                this._front = node;
                if (this._size % 2 == 0) {
                    this._middle = this._middle.previous();
                }
            }
        }
    }
    

    public void push_front(A first_element, B second_element) {
        Nodo_Par<A, B> node;
        this._size = this._size + 1;
        if (is_empty()) {
            node = new Nodo_Par<>(first_element,second_element);
            this._front = this._back = this._middle = node;
        } else {
            node = new Nodo_Par<>(null, first_element,second_element, this._front);
            this._front.previous(node);
            this._front = node;
            if (this._size % 2 == 0) {
                this._middle = this._middle.previous();
            }
        }
    }
    
    public void print() {
        Nodo_Par<A,B> iterator = this._front;
        while (iterator != null) {
            System.out.println(iterator.element());
            iterator = iterator.next();
        }
    }

    public boolean contains(Par<A,B> element) {

        if (_size == 0) {
            return false;
        }
        if (_size == 1) {
            return this.first().equals(element);
        }
        if (_size == 2) {
            return this.first().equals(element) || this.last().equals(element);
        } else {
            Nodo_Par<A,B> first = this._front;
            Nodo_Par<A,B> last = this._back;

            int middle_index = (this._size) / 2;

            if (middle_index % 2 == 0) {
                for (int i = 0; i < middle_index; i++) {

                    if (first.element().equals(element)) {
                        return true;
                    }

                    if (last.element().equals(element)) {
                        return true;
                    }

                    first = first.next();
                    last = last.previous();
                }
                return false;
            } else {
                for (int i = 0; i < middle_index; i++) {

                    if (first.element().equals(element)) {
                        return true;
                    }

                    if (last.element().equals(element)) {
                        return true;
                    }

                    first = first.next();
                    last = last.previous();
                }

                return this.medium().equals(element);
            }
        }
    }

    public boolean contains(A first_element, B second_element) {
        if (_size == 0) {
            return false;
        }
        if (_size == 1) {
            return this.first().equals(first_element,second_element);
        }
        if (_size == 2) {
            return this.first().equals(first_element,second_element) || this.last().equals(first_element,second_element);
        } else {
            Nodo_Par<A, B> first = this._front;
            Nodo_Par<A, B> last = this._back;

            int middle_index = (this._size) / 2;

            if (middle_index % 2 == 0) {
                for (int i = 0; i < middle_index; i++) {

                    if (first.element().equals(first_element,second_element)) {
                        return true;
                    }

                    if (last.element().equals(first_element,second_element)) {
                        return true;
                    }

                    first = first.next();
                    last = last.previous();
                }
                return false;
            } else {
                for (int i = 0; i < middle_index; i++) {

                    if (first.element().equals(first_element,second_element)) {
                        return true;
                    }

                    if (last.element().equals(first_element,second_element)) {
                        return true;
                    }

                    first = first.next();
                    last = last.previous();
                }

                return this.medium().equals(first_element,second_element);
            }
        }
    }

    public boolean contains_first(A first_element) {

        if (_size == 0) {
            return false;
        }
        if (_size == 1) {
            return this.first().first().equals(first_element);
        }
        if (_size == 2) {
            return this.first().first().equals(first_element) || this.last().first().equals(first_element);
        } else {
            Nodo_Par<A,B> first = this._front;
            Nodo_Par<A,B> last = this._back;

            int middle_index = (this._size) / 2;

            if (middle_index % 2 == 0) {
                for (int i = 0; i < middle_index; i++) {

                    if (first.element().first().equals(first_element)) {
                        return true;
                    }

                    if (last.element().first().equals(first_element)) {
                        return true;
                    }

                    first = first.next();
                    last = last.previous();
                }
                return false;
            } else {
                for (int i = 0; i < middle_index; i++) {

                    if (first.element().first().equals(first_element)) {
                        return true;
                    }

                    if (last.element().first().equals(first_element)) {
                        return true;
                    }

                    first = first.next();
                    last = last.previous();
                }

                return this.medium().first().equals(first_element);
            }
        }
    }
    
    public boolean contains_second(B second_element){

        if (_size == 0) {
            return false;
        }
        if (_size == 1) {
            return this.first().second().equals(second_element);
        }
        if (_size == 2) {
            return this.first().second().equals(second_element) || this.last().second().equals(second_element);
        } else {
            Nodo_Par<A,B> first = this._front;
            Nodo_Par<A,B> last = this._back;

            int middle_index = (this._size) / 2;

            if (middle_index % 2 == 0) {
                for (int i = 0; i < middle_index; i++) {

                    if (first.element().second().equals(second_element)) {
                        return true;
                    }

                    if (last.element().second().equals(second_element)) {
                        return true;
                    }

                    first = first.next();
                    last = last.previous();
                }
                return false;
            } else {
                for (int i = 0; i < middle_index; i++) {

                    if (first.element().second().equals(second_element)) {
                        return true;
                    }

                    if (last.element().second().equals(second_element)) {
                        return true;
                    }

                    first = first.next();
                    last = last.previous();
                }

                return this.medium().second().equals(second_element);
            }
        }
    }
    
    public boolean add_front(Par<A,B> element) {
        boolean contains = contains(element);
        if (!contains) {
            push_front(element);
        }
        return contains;
    }

    public boolean add_front(A first_element, B second_element) {
        boolean contains = contains(first_element,second_element);
        if (!contains) {
            push_front(first_element,second_element);
        }
        return contains;
    }
    
    public boolean add_back(Par<A,B> element) {
        boolean contains = contains(element);
        if (!contains) {
            push_back(element);
        }
        return contains;
    }
    
    public boolean add_back(A first_element, B second_element) {
        boolean contains = contains(first_element,second_element);
        if (!contains) {
            push_back(first_element,second_element);
        }
        return contains;
    }

    public Par<A,B> get(int index) throws NullPointerException {
        if (!is_empty()) {
            int real_index = index % this._size;
            if (real_index == 0) {
                return this._front.element();
            } else if (real_index == _size - 1) {
                return this._back.element();
            } else {
                int middle_index = (this._size % 2 != 0) ? (this._size / 2) : (this._size / 2) - 1;

                int difference_front = Math.abs(real_index - 1);
                int difference_middle = Math.abs(real_index - middle_index);
                int difference_back = Math.abs(real_index - (_size - 2));

                //Difference Front Is Pair<A,B>he Best
                if (is_best(difference_front, difference_middle, difference_back)) {
                    Nodo_Par<A,B> auxiliar = this._front.next();
                    for (int i = 1; i < real_index; i++) {
                        auxiliar = auxiliar.next();
                    }
                    return auxiliar.element();
                } //Difference Back Is Pair<A,B>he Best
                else if (is_best(difference_back, difference_front, difference_middle)) {
                    Nodo_Par<A,B> auxiliar = this._back.previous();
                    for (int i = this._size - 2; i > real_index; i--) {
                        auxiliar = auxiliar.previous();
                    }
                    return auxiliar.element();
                } //Difference Middle Is Pair<A,B>he Best
                else {
                    if (real_index > middle_index) {
                        Nodo_Par<A,B> auxiliar = this._middle;
                        for (int i = middle_index; i < real_index; i++) {
                            auxiliar = auxiliar.next();
                        }
                        return auxiliar.element();
                    } else if (real_index < middle_index) {
                        Nodo_Par<A,B> auxiliar = this._middle;
                        for (int i = middle_index; i > real_index; i--) {
                            auxiliar = auxiliar.previous();
                        }
                        return auxiliar.element();
                    } else {
                        return this._middle.element();
                    }
                }
            }
        }

        return null;
    }

    public Par<A,B> get_from_first(A first_element) {

        if (_size == 0) {
            return null;
        }
        if (_size == 1) {
            return this.first().first().equals(first_element) ? this.first() : null;
        }
        if (_size == 2) {
            return this.first().first().equals(first_element) ? this.first() : (this.last().first().equals(first_element) ? this.last() : null);
        } else {
            Nodo_Par<A,B> first = this._front;
            Nodo_Par<A,B> last = this._back;

            int middle_index = (this._size) / 2;

            if (middle_index % 2 == 0) {
                for (int i = 0; i < middle_index; i++) {

                    if (first.element().first().equals(first_element)) {
                        return first.element();
                    }

                    if (last.element().first().equals(first_element)) {
                        return last.element();
                    }

                    first = first.next();
                    last = last.previous();
                }
                return null;
            } else {
                for (int i = 0; i < middle_index; i++) {

                    if (first.element().first().equals(first_element)) {
                        return first.element();
                    }

                    if (last.element().first().equals(first_element)) {
                        return last.element();
                    }

                    first = first.next();
                    last = last.previous();
                }

                return this.medium().first().equals(first_element) ? this.medium() : null;
            }
        }
    }
    
    public Par<A,B> get_from_second(B second_element) {

        if (_size == 0) {
            return null;
        }
        if (_size == 1) {
            return this.first().second().equals(second_element) ? this.first() : null;
        }
        if (_size == 2) {
            return this.first().second().equals(second_element) ? this.first() : (this.last().second().equals(second_element) ? this.last() : null);
        } else {
            Nodo_Par<A,B> first = this._front;
            Nodo_Par<A,B> last = this._back;

            int middle_index = (this._size) / 2;

            if (middle_index % 2 == 0) {
                for (int i = 0; i < middle_index; i++) {

                    if (first.element().second().equals(second_element)) {
                        return first.element();
                    }

                    if (last.element().second().equals(second_element)) {
                        return last.element();
                    }

                    first = first.next();
                    last = last.previous();
                }
                return null;
            } else {
                for (int i = 0; i < middle_index; i++) {

                    if (first.element().second().equals(second_element)) {
                        return first.element();
                    }

                    if (last.element().second().equals(second_element)) {
                        return last.element();
                    }

                    first = first.next();
                    last = last.previous();
                }

                return this.medium().second().equals(second_element) ? this.medium() : null;
            }
        }
    }
    
    public void replace(Par<A,B> element_to_replace, Par<A,B> replacement) {

        Nodo_Par<A,B> node = node(element_to_replace);

        if (node != null) {
            node.element(replacement);
        }

    }

    public void replacing(Par<A,B> element_to_replace, Par<A,B> replacement) {

        if (_size == 0) {
            return;
        }
        if (_size == 1) {
            if (_front.element().equals(element_to_replace)) {
                _front.element(replacement);
            }
        }
        if (_size == 2) {
            if (_front.element().equals(element_to_replace)) {
                _front.element(replacement);
            }
            if (_back.element().equals(element_to_replace)) {
                _back.element(replacement);
            }
        } else {
            Nodo_Par<A,B> first = this._front;
            Nodo_Par<A,B> last = this._back;

            int middle_index = (this._size) / 2;

            if (middle_index % 2 == 0) {
                for (int i = 0; i < middle_index; i++) {

                    if (first.element().equals(element_to_replace)) {
                        first.element(replacement);
                    }

                    if (last.element().equals(element_to_replace)) {
                        last.element(replacement);
                    }

                    first = first.next();
                    last = last.previous();
                }
            } else {
                for (int i = 0; i < middle_index; i++) {

                    if (first.element().equals(element_to_replace)) {
                        first.element(replacement);
                    }

                    if (last.element().equals(element_to_replace)) {
                        last.element(replacement);
                    }

                    first = first.next();
                    last = last.previous();
                }

                if (_middle.element().equals(element_to_replace)) {
                    _middle.element(replacement);
                }
            }

        }
    }

    
    public void replace(int position, Par<A,B> replacement) {

        Nodo_Par<A,B> node = node(position);

        if (node != null) {
            node.element(replacement);
        }

    }

    
    public void superSede(Par<A,B> element_to_replace, Par<A,B> replacement) {
        if (!contains(replacement)) {
            replace(element_to_replace, replacement);
        }
    }

    
    public void superSede(int position, Par<A,B> replacement) {
        if (!contains(replacement)) {
            replace(position, replacement);
        }
    }

    
    public void swap(int firstPosition, int secondPosition) {
        if (!is_empty()) {
            if (firstPosition != secondPosition) {

                Nodo_Par<A,B> first = node(firstPosition);
                Nodo_Par<A,B> second = node(secondPosition);

                Par<A,B> auxiliar = second.element();
                second.element(first.element());
                first.element(auxiliar);
            }
        }
    }

    
    public void swap(Par<A,B> firstElement, Par<A,B> secondElement) {
        if (!is_empty()) {

            Nodo_Par<A,B> first_node = node(firstElement);
            Nodo_Par<A,B> second_node = node(secondElement);

            if (first_node != null && second_node != null) {

                Par<A,B> auxiliar = first_node.element();
                first_node.element(second_node.element());
                second_node.element(auxiliar);
            }
        }
    }

    
    public int position(Par<A,B> element) {
        if (_size == 0) {
            return -1;
        }
        if (_size == 1) {
            return this._front.element().equals(element) ? 0 : -1;
        }
        if (_size == 2) {
            return this._front.element().equals(element) ? 0 : (this._back.element().equals(element) ? 1 : -1);
        } else {
            Nodo_Par<A,B> first = this._front;
            Nodo_Par<A,B> last = this._back;

            int middle_index = (this._size) / 2;

            if (middle_index % 2 == 0) {
                int first_index = 0;
                int last_index = _size - 1;
                for (int i = 0; i < middle_index; i++) {

                    if (first.element().equals(element)) {
                        return first_index;
                    }

                    if (last.element().equals(element)) {
                        return last_index;
                    }

                    first_index++;
                    last_index--;
                    first = first.next();
                    last = last.previous();
                }
                return -1;
            } else {
                int first_index = 0;
                int last_index = _size - 1;
                for (int i = 0; i < middle_index; i++) {

                    if (first.element().equals(element)) {
                        return first_index;
                    }

                    if (last.element().equals(element)) {
                        return last_index;
                    }

                    first_index++;
                    last_index--;
                    first = first.next();
                    last = last.previous();
                }

                return _middle.element().equals(element) ? middle_index : -1;
            }
        }
    }

    
    public int node_position(Nodo_Par node) {
        if (_size == 0) {
            return -1;
        }
        if (_size == 1) {
            return this._front.equals(node) ? 0 : -1;
        }
        if (_size == 2) {
            return this._front.equals(node) ? 0 : (this._back.equals(node) ? 1 : -1);
        } else {
            Nodo_Par<A,B> first = this._front;
            Nodo_Par<A,B> last = this._back;

            int middle_index = (this._size) / 2;

            if (middle_index % 2 == 0) {
                int first_index = 0;
                int last_index = _size - 1;
                for (int i = 0; i < middle_index; i++) {

                    if (first.equals(node)) {
                        return first_index;
                    }

                    if (last.equals(node)) {
                        return last_index;
                    }

                    first_index++;
                    last_index--;
                    first = first.next();
                    last = last.previous();
                }
                return -1;
            } else {
                int first_index = 0;
                int last_index = _size - 1;
                for (int i = 0; i < middle_index; i++) {

                    if (first.equals(node)) {
                        return first_index;
                    }

                    if (last.equals(node)) {
                        return last_index;
                    }

                    first_index++;
                    last_index--;
                    first = first.next();
                    last = last.previous();
                }

                return _middle.equals(node) ? middle_index : -1;
            }
        }
    }

    
    public void pop_front() {
        if (!is_empty()) {
            if (this._size % 2 == 0 && this.middle() != null) {
                this._middle = this._middle.next();
            }

            Nodo_Par<A,B> erased_node = this._front;
            this._front = erased_node.next();

            if (this._front != null) {

                this._front.previous(null);
                erased_node.dispose();
            }

            --this._size;

        }
    }

    
    public void pop_back() {
        if (!is_empty()) {

            if (this._size % 2 != 0 && this.middle() != null) {
                this._middle = this._middle.previous();
            }

            Nodo_Par<A,B> erased_node = this._back;
            this._back = erased_node.previous();

            if (this._back != null) {

                this._back.next(null);
                erased_node.dispose();
            }

            --this._size;
        }
    }

    
    public void join_back(Lista_Pares<A,B> list) {
        if (list != null) {
            Nodo_Par<A,B> auxiliar = list.front();

            while (auxiliar != null) {
                this.push_back(auxiliar.element());
                auxiliar = auxiliar.next();
            }
        }
    }

    
    public void join_front(Lista_Pares<A,B> list) {
        if (list != null) {
            Nodo_Par<A,B> auxiliar = list.front();

            while (auxiliar != null) {
                this.push_front(auxiliar.element());
                auxiliar = auxiliar.next();
            }
        }
    }

    
    public void concatenate_front(Lista_Pares<A,B> list) {
        if (list != null) {
            Nodo_Par<A,B> auxiliar = list.front();

            while (auxiliar != null) {
                this.add_front(auxiliar.element());
                auxiliar = auxiliar.next();
            }
        }
    }

    
    public void concatenate_back(Lista_Pares<A,B> list) {
        if (list != null) {
            Nodo_Par<A,B> auxiliar = list.front();

            while (auxiliar != null) {
                this.add_back(auxiliar.element());
                auxiliar = auxiliar.next();
            }
        }
    }

    
    public void reverse() {

        if (_size <= 1) {
            return;
        }
        if (_size == 2) {
            Par<A,B> first_value = _front.element();
            _front.element(_back.element());
            _back.element(first_value);
        } else {
            Nodo_Par<A,B> first = this._front;
            Nodo_Par<A,B> last = this._back;

            int middle_index = (this._size) / 2;
            Par<A, B> first_value;
            for (int i = 0; i < middle_index; i++) {

                first_value = first.element();
                first.element(last.element());
                last.element(first_value);

                first = first.next();
                last = last.previous();
            }
        }
    }

    
    public void clear() {
        if (!is_empty()) {
            while (!is_empty()) {
                pop_front();
            }
            this._size = 0;
            this._back = this._middle = this._front = null;
        }
    }

    private void update_middle() {
        Nodo_Par<A,B> aux = this._front;
        int middle_index = ((this._size % 2 == 0)) ? ((this._size) / 2) - 1 : (this._size) / 2;

        for (int i = 0; i < middle_index; i++) {
            aux = aux.next();
        }

        this._middle = aux;

    }

    
    public void insert_next(Nodo_Par<A,B> node, Par<A,B> element) {
        if (!is_empty()) {

            if (node != null) {
                if (node.equals(this._back)) {
                    push_back(element);
                } else {
                    Nodo_Par<A,B> next_node = node.next();
                    Nodo_Par<A,B> new_node = new Nodo_Par<>(node, element.first(),element.second(), next_node);

                    node.next(new_node);
                    if (next_node != null) {
                        next_node.previous(new_node);
                    }

                    this._size = this._size + 1;

                    update_middle();
                }
            }
        }
    }

    
    public void insert_previous(Nodo_Par<A,B> node, Par<A,B> element) {
        if (!is_empty()) {

            if (node != null) {
                if (node.equals(this._front)) {
                    push_front(element);
                } else {

                    Nodo_Par<A,B> previous_node = node.previous();
                    Nodo_Par<A,B> new_node = new Nodo_Par<>(previous_node, element.first(),element.second(), node);

                    node.previous(new_node);
                    if (previous_node != null) {
                        previous_node.next(new_node);
                    }

                    this._size = this._size + 1;
                    update_middle();
                }
            }
        }
    }

    
    public void insert_at(int position, Par<A,B> element) {
        if (!is_empty()) {
            position = position % this._size;
            if (position == 0) {
                push_front(element);
            } else if (position == this._size - 1) {
                push_back(element);
            } else {
                Nodo_Par<A,B> node = node(position);
                this.insert_previous(node, element);
            }
        }
    }

    
    public void introduce_at(int index, Par<A,B> element) {

        if (!contains(element)) {
            insert_at(index, element);
        }
    }

    
    public void erase_at(int position) {
        if (!is_empty()) {

            int real_position = position % this._size;

            if (real_position == 0) {
                pop_front();
            } else if (real_position == (this._size - 1)) {
                pop_back();
            } else {

                Nodo_Par<A,B> node = node(position);
                if (node != null) {
                    Nodo_Par<A,B> previous_node = node.previous();
                    Nodo_Par<A,B> next_node = node.next();

                    if (previous_node != null) {
                        previous_node.next(next_node);
                    }

                    if (next_node != null) {
                        next_node.previous(previous_node);
                    }

                    node.dispose();

                    this._size--;
                    update_middle();
                }

            }
        }
    }

    
    public void remove(Par<A,B> element) {
        if (!is_empty()) {
            if (this.first().equals(element)) {
                pop_front();
            } else if (this.last().equals(element)) {
                pop_back();
            } else {

                Nodo_Par<A,B> node = node(element);
                if (node != null) {
                    Nodo_Par<A,B> previous_node = node.previous();
                    Nodo_Par<A,B> next_node = node.next();

                    if (previous_node != null) {
                        previous_node.next(next_node);
                    }

                    if (next_node != null) {
                        next_node.previous(previous_node);
                    }

                    node.dispose();

                    this._size--;
                    update_middle();
                }

            }
        }
    }

    
    public Nodo_Par<A,B> node(Par<A,B> element) throws NullPointerException {

        if (_size == 0) {
            return null;
        }
        if (_size == 1) {
            return this.first().equals(element) ? _front : null;
        }
        if (_size == 2) {
            return this.first().equals(element) ? _front : (this.last().equals(element) ? _back : null);
        } else {
            Nodo_Par<A,B> first = this._front;
            Nodo_Par<A,B> last = this._back;

            int middle_index = (this._size) / 2;

            if (middle_index % 2 == 0) {
                for (int i = 0; i < middle_index; i++) {

                    if (first.element().equals(element)) {
                        return first;
                    }

                    if (last.element().equals(element)) {
                        return last;
                    }

                    first = first.next();
                    last = last.previous();
                }
                return null;
            } else {
                for (int i = 0; i < middle_index; i++) {

                    if (first.element().equals(element)) {
                        return first;
                    }

                    if (last.element().equals(element)) {
                        return last;
                    }

                    first = first.next();
                    last = last.previous();
                }

                return _middle.element().equals(element) ? _middle : null;
            }
        }

    }

    public Nodo_Par<A,B> node(A first_element, B second_element) throws NullPointerException {

        if (_size == 0) {
            return null;
        }
        if (_size == 1) {
            return this.first().equals(first_element,second_element) ? _front : null;
        }
        if (_size == 2) {
            return this.first().equals(first_element,second_element) ? _front : (this.last().equals(first_element,second_element) ? _back : null);
        } else {
            Nodo_Par<A,B> first = this._front;
            Nodo_Par<A,B> last = this._back;

            int middle_index = (this._size) / 2;

            if (middle_index % 2 == 0) {
                for (int i = 0; i < middle_index; i++) {

                    if (first.element().equals(first_element,second_element)) {
                        return first;
                    }

                    if (last.element().equals(first_element,second_element)) {
                        return last;
                    }

                    first = first.next();
                    last = last.previous();
                }
                return null;
            } else {
                for (int i = 0; i < middle_index; i++) {

                    if (first.element().equals(first_element,second_element)) {
                        return first;
                    }

                    if (last.element().equals(first_element,second_element)) {
                        return last;
                    }

                    first = first.next();
                    last = last.previous();
                }

                return _middle.element().equals(first_element,second_element) ? _middle : null;
            }
        }

    }
    
    public Nodo_Par<A,B> node(int index) throws NullPointerException {
        if (!is_empty()) {
            int real_index = index % this._size;
            if (real_index == 0) {
                return this._front;
            } else if (real_index == _size - 1) {
                return this._back;
            } else {
                int middle_index = (this._size % 2 != 0) ? (this._size / 2) : (this._size / 2) - 1;

                int difference_front = Math.abs(real_index - 1);
                int difference_middle = Math.abs(real_index - middle_index);
                int difference_back = Math.abs(real_index - (_size - 2));

                //Difference Front Is Pair<A,B>he Best
                if (is_best(difference_front, difference_middle, difference_back)) {
                    Nodo_Par<A,B> auxiliar = this._front.next();
                    for (int i = 1; i < real_index; i++) {
                        auxiliar = auxiliar.next();
                    }
                    return auxiliar;
                } //Difference Back Is Pair<A,B>he Best
                else if (is_best(difference_back, difference_front, difference_middle)) {
                    Nodo_Par<A,B> auxiliar = this._back.previous();
                    for (int i = this._size - 2; i > real_index; i--) {
                        auxiliar = auxiliar.previous();
                    }
                    return auxiliar;
                } //Difference Middle Is Pair<A,B>he Best
                else {
                    if (real_index > middle_index) {
                        Nodo_Par<A,B> auxiliar = this._middle;
                        for (int i = middle_index; i < real_index; i++) {
                            auxiliar = auxiliar.next();
                        }
                        return auxiliar;
                    } else if (real_index < middle_index) {
                        Nodo_Par<A,B> auxiliar = this._middle;
                        for (int i = middle_index; i > real_index; i--) {
                            auxiliar = auxiliar.previous();
                        }
                        return auxiliar;
                    } else {
                        return this._middle;
                    }
                }

            }
        }

        return null;
    }

    //Override methods
    
    @Override
    public String toString() throws NullPointerException {

        String newString = new String();

        Nodo_Par<A,B> auxiliar = this._front;

        while (auxiliar != null) {
            newString += "[" + auxiliar.element() + "]\n";
            auxiliar = auxiliar.next();
        }
        return newString;
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        Nodo_Par<A,B> aux = this._front;
        while (aux != null) {
            hash = 53 * hash + aux.element().hashCode();
            aux = aux.next();
        }

        hash = 53 * hash + (int) (this._size ^ (32 >>> this._size));
        return hash;
    }

    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Lista_Pares<?,?>)) {
            return false;
        }

        Lista_Pares<A,B> other = (Lista_Pares<A,B>) obj;

        if (other.size() == this._size) {
            Nodo_Par<A,B> thisAux = this._front;
            Nodo_Par<A,B> otherAux = other.front();

            while (thisAux != null) {

                if (!thisAux.element().equals(otherAux.element())) {
                    return false;
                }

                thisAux = thisAux.next();
                otherAux = otherAux.next();

            }

            return true;
        }

        return false;

    }

    private boolean is_best(int first, int second, int third) {
        return first <= second && first <= third;
    }
}
