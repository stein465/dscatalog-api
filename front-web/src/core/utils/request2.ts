import axios, { Method } from 'axios';

export type RequestParams = {
    method? : Method,
    url : string,
    data? : object,
    params?: object

}

export const BASE_URL2 = "http://localhost:3000";

export const MakeRequest = ( {method = "GET", url, data, params}:RequestParams) => {

    return axios ({
        method,
        url : `${BASE_URL2}${url}`,
        data,
        params
    })
}

   

