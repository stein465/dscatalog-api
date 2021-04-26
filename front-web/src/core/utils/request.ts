import axios, {Method} from 'axios';


// configurar as request como utilitario universal para aplicação



type requestParams= {
    method?: Method;  // em request mais simples nao é necessario passa o method pois o padrao é get
    url: string;
    data?: object;    // em request mais simples nao é necessario passar o data pois está apenas fazendo requisição
    params?: object;  //parametros de pagina não são obrigatorios
}

const BASE_URL = 'http://localhost:3000';

//função para fazer as requests
export const makeRequest = ({method='GET', url, data, params}: requestParams) => {
    //objeto de configuração para os parametros dinamicos
    return axios({
        method,
        url: `${BASE_URL}${url}`,
        data,
        params
    });
}


