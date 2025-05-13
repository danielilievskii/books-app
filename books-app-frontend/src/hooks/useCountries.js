import {useCallback, useEffect, useState} from "react";
import {countryRepository} from "../repository/countryRepository.js";

const initialState = {
    countries: [],
    loading: true,
}
export const useCountries = () => {
    const [state, setState] = useState(initialState)

    const fetchCountries = useCallback(() => {
        countryRepository
            .findAll()
            .then(response => {
                setState({
                    countries: response.data,
                    loading: false,
                })
            })
            .catch(error => console.log(error))
    }, [])

    const onAdd = useCallback((data) => {
        countryRepository
            .add(data)
            .then(() => {
                fetchCountries()
            })
            .catch(error => console.log(error))
    }, [fetchCountries])

    const onEdit = useCallback((id, data) => {
        countryRepository
            .edit(id, data)
            .then(() => {
                fetchCountries()
            })
            .catch(error => console.log(error))
    }, [fetchCountries])

    const onDelete = useCallback((id) => {
        countryRepository
            .delete(id)
            .then(() => {
                fetchCountries()
            })
            .catch(error => console.log(error))
    }, [fetchCountries])

    useEffect(() => {
        fetchCountries()
    }, [fetchCountries]);

    return {...state, onAdd: onAdd, onEdit: onEdit, onDelete: onDelete};
}