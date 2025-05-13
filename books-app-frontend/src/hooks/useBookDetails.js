import {useCallback, useEffect, useState} from "react";
import {bookRepository} from "../repository/bookRepository.js";

const initialState = {
    book: {},
    loading: true,
}

export const useBookDetails = (id) => {
    const [state, setState] = useState(initialState);

    const fetchBook = useCallback((bookId) => {
        bookRepository
            .findById(bookId)
            .then(response => {
                setState({
                    book: response.data,
                    loading: false,
                })
            })
            .catch(error => {
                console.log(error);
            })
    }, [])
    
    useEffect(() => {
        fetchBook(id)
    }, [fetchBook, id])

    return state;

}