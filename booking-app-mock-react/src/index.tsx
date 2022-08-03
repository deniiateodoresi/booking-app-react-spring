import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
// import App from './App';
import reportWebVitals from './reportWebVitals';
import {PageTemplateLayout} from "./layout/PageTemplateLayout";
import {BrowserRouter, Link, Route, Routes} from "react-router-dom";
import {DetailsPageLayout} from "./layout/DetailsPageLayout";
import {Provider} from "react-redux";
import {store} from "./store";


const root = ReactDOM.createRoot(
    document.getElementById('root') as HTMLElement
);
root.render(
    <React.StrictMode>
        <Provider store={store}>
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<PageTemplateLayout  name="main-info"/>}/>
                    <Route path="/DetailsPageLayout" element={<DetailsPageLayout />}/>
                </Routes>
            </BrowserRouter>
        </Provider>
    </React.StrictMode>);
reportWebVitals();


// useNavigate, state -> accommodation
// useLocation, location.state

// redux