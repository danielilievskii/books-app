import {createContext, useEffect, useState} from "react";
import {userRepository} from "../repository/userRepository.js";

export const UserContext = createContext(undefined);

export const UserContextProvider = ({children}) => {
    const [user, setUser] = useState(null);

    useEffect(() => {
        const fetchUser = () => {
            return userRepository
                .me()
                .then((response) => {
                    setUser(response.data);
                })
                .catch((error) => {
                    console.log(error);
                })
        }

        fetchUser();

    }, [])

    return (
        <UserContext.Provider value={{user, setUser}}>
            {children}
        </UserContext.Provider>
    )
}

