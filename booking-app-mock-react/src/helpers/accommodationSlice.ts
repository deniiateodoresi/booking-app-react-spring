import {AccommodationUnitDTO} from "../types/Accommodation";
import {createSlice} from "@reduxjs/toolkit";

export interface AccommodationState{
    accommodation: AccommodationUnitDTO
}

const initialState: AccommodationState = {
    accommodation: {
        name:"",
        description: "",
        review: 0,
        remarks: [],
        price: 0,
        town: "",
        type: ""
    }
}

export const accommodationSlice = createSlice({
    name: "displayer",
    initialState,
    reducers: {
        modifiedAccommodation: (state, action) => {
            state.accommodation = action.payload
        }
    },
})

export const { modifiedAccommodation } = accommodationSlice.actions
