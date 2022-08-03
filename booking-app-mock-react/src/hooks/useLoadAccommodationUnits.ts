import {useEffect, useState} from 'react';
import axios from "axios";

export const useLoadAccommodationUnits = () => {
    const [accommodations, setAccommodations] = useState([]);

    useEffect(() => {
        getAllAccommodations();
    }, []);

    const getAllAccommodations = () => {
        axios.get('http://localhost:9090/accommodations').then(
            function (response) {
                const allAccommodations = response.data;
                setAccommodations(allAccommodations);
            }
        )
    }
    return {accommodations, getAllAccommodations};
}