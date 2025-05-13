import {useCallback, useEffect, useState} from "react";
import {authorRepository} from "../repository/authorRepository.js";

const initialState = {
    authors: [],
    loading: true,
}

export const useAuthors = () => {
    const [state, setState] = useState(initialState)

    const fetchAuthors = useCallback(() => {
        authorRepository
            .findAll()
            .then(response => {
                setState({
                    authors: response.data,
                    loading: false,
                })
            })
            .catch(error => console.log(error))
    }, [])

    const onAdd = useCallback((data) => {
        authorRepository
            .add(data)
            .then(() => {
                fetchAuthors()
            })
            .catch(error => console.log(error))
    }, [fetchAuthors])

    const onEdit = useCallback((id, data) => {
        authorRepository
            .edit(id, data)
            .then(() => {
                fetchAuthors()
            })
            .catch(error => console.log(error))
    }, [fetchAuthors])

    const onDelete = useCallback((id) => {
        authorRepository.delete(id)
            .then(() => {
                fetchAuthors()
            })
            .catch(error => console.log(error))
    }, [fetchAuthors])

    useEffect(() => {
        fetchAuthors()
    }, [fetchAuthors]);

    return {...state, onAdd: onAdd, onEdit: onEdit, onDelete: onDelete};
}