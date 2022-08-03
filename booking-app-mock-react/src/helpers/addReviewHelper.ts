import axios from "axios";

export const addReviewHelper = (name: string, rating: number | null, remarks: string) => {

    axios.put('http://localhost:9090/accommodations',null,
        {
                params:
                    {
                        name: name,
                        review: rating,
                        remarks: remarks
                }
        })

}