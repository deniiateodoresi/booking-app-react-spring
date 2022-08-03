import {configureStore} from "@reduxjs/toolkit";
import {configure} from "@testing-library/react";
import {accommodationSlice} from "./helpers/accommodationSlice";

export const store = configureStore({
    reducer: {
        displayer: accommodationSlice.reducer
    },
})

export type RootState = ReturnType<typeof store.getState>

export type AppDispatch = typeof store.dispatch

// View/App --- dispatch ---> action (changeName) --- handleAction, change state --->
// ---> reducer (ex: has a method that changes the name, executes something on the old state to give us the new state)
// --- store state ---> store --- subscribe / pass state ---> View/App


// oldState --- stays untouch

// MainComponent --- dispatch action and reduce state ---> store ---
// --- pass state ---> other components