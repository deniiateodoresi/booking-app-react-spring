import {Grid, Typography} from "@mui/material";
import {PageHeader} from "./base/PageHeader";
import React, {useEffect, useState} from "react";
import {ReviewForm} from "./base/ReviewForm";
import {useSelector} from "react-redux";
import {RootState} from "../store";
import {ReviewsList} from "./ReviewsList";

export function DetailsPageLayout () {

    // before: passing data with useNavigate and state option
    // const location = useLocation();
    // const props = location.state as AccommodationUnitDTO;

    const props = useSelector((state: RootState) => state.displayer.accommodation)

    const [remarks, setRemarks] = useState<string[]>([]);
    const [isStarted, setIsStarted] = useState(false);

    useEffect( () => {
        if(!isStarted)
            setRemarks(props.remarks);
        setIsStarted(true);
    }, [remarks])

    const handleAddedReview = (remark: string) => {
        const copyRemarks: string[] = [];
        remarks.map((oldRemark: string) => {
            copyRemarks.push(oldRemark);
        })
        copyRemarks.push(remark);
        setRemarks(copyRemarks);
    }

    return (
        <Grid container sx={{ height: '100vh' }} >
            <PageHeader />
            <Grid container sx={{backgroundColor: '#c8d5ff', height: 'calc(100vh - 100px)'}}>
                <Grid xs={7.5} container item sx={{backgroundColor: "#E0C7A9"}}>
                    <Grid>

                    </Grid>
                    <Grid container item xs={12} direction="column" alignItems="center">
                        <Typography variant="h4">{props.name}</Typography>
                        <Typography>{props.description}</Typography>
                    </Grid>
                </Grid>
                <Grid container item xs={4.5} sx={{ overflowY: "scroll", height: 'calc(100vh - 100px)'}}>
                    <ReviewForm name={props.name} handleAddedReview={handleAddedReview} />
                    {
                       !isStarted ? <div></div> : <ReviewsList reviews={remarks} />
                    }
                </Grid>
            </Grid>
        </Grid>
    );
}