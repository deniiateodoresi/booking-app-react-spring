import React from "react";
import {AccommodationUnitDTO} from "../types/Accommodation";
import {AccommodationCard} from "./base/AccommodationCard";
import {Grid} from "@mui/material";

export function AccommodationList (props: {accommodations: AccommodationUnitDTO[], getAllAccommodations: () => void}){

    const DisplayAccommodations = () => {
        if(props.accommodations.length > 0){
            return (
                props.accommodations.map((accommodation: AccommodationUnitDTO, index) => {
                    const key = "accommodation_" + index;
                    return <AccommodationCard key={key} accommodation={accommodation} getAllAccommodations={props.getAllAccommodations} />
                })
            );
        }
    }

    return (
        <Grid>
            <>{DisplayAccommodations()}</>
        </Grid>
    )
}