import {useEffect, useState} from "react";
import {AxiosResponse, default as axios} from "axios";

export const useLoadTowns = () => {
    const[towns, setTowns] = useState([]);

    useEffect(() =>{
        getAllTowns();
    }, []);

    const getAllTowns = () => {
        axios.get('http://localhost:9090/towns').then(
            function (response: AxiosResponse) {
                const allTowns = response.data;
                setTowns(allTowns);
            });
    }
    return {towns};
}