import http from 'k6/http';
import { check, sleep } from 'k6';
// This will export to HTML as filename "result.html" AND also stdout using the text summary
import { htmlReport } from "https://raw.githubusercontent.com/benc-uk/k6-reporter/main/dist/bundle.js";
import { textSummary } from "https://jslib.k6.io/k6-summary/0.0.1/index.js";

export function handleSummary(data) {
  return {
    "result.html": htmlReport(data),
    stdout: textSummary(data, { indent: " ", enableColors: true }),
  };
}

export const options = {
  // // A number specifying the number of VUs to run concurrently.
  // vus: 10,
  // // A string specifying the total duration of the test run.
  // duration: '4s',

  // define stages
  stages: [
    { duration: '15s', target: 50 },
    { duration: '15s', target: 120 },
    { duration: '1m01s', target: 100 },
    { duration: '4s', target: 5 },
  ],
  // stages: [
  //   { duration: '20s', target: 200 },
  //   { duration: '1m01s', target: 100 },
  //   { duration: '4s', target: 5 },
  // ],
  // define thresholds
  thresholds: {
    http_req_failed: ['rate<0.01'], // http errors should be less than 1%
    http_req_duration: ['p(99)<1000'], // 99% of requests should be below 1s
  },
};

const examplePost = (url, jsonPayload) => {

  const payload = JSON.stringify(jsonPayload);
  const params = {
    headers: {
      "Content-Type": "application/json",
    },
  };

  // send a post request and save response as a variable
  const res = http.post(url, payload, params);
  sleep(1);

  // check that response is 200
  check(res, {
    "response code was 201": (res) => res.status == 201,
  });
}

const exampleGet = (url) => {
  const res = http.get(url);
  sleep(1);

  // check that response is 200
  check(res, {
    "response code was 200": (res) => res.status == 200,
  });
}

// The function that defines VU logic.
//
// See https://grafana.com/docs/k6/latest/examples/get-started-with-k6/ to learn more
// about authoring k6 scripts.
//
export default function () {
  exampleGet("http://java17:8083/api/resumen")
  examplePost("http://java17:8083/api/resumen/modificar-total-disponible", {
    valor: Math.round(Math.random()*1000000)/10
  })

  examplePost("http://java17:8083/api/presupuesto", {
    "nombre": "prueba random",
    "descripcion": "prueba cualquiera",
    "porcentual": false,
    "valor": 1
  })
  exampleGet("http://java17:8083/api/presupuesto")

  const ingresoParaLuegoGastar = Math.floor(Math.random() * 1000) / 100;
  examplePost("http://java17:8083/api/ingreso", {
    "descripcion": "ingreso prueba",
    "valor": ingresoParaLuegoGastar
  })

  examplePost("http://java17:8083/api/gasto", {
    "idPresupuesto": 1,
    "descripcion": "gasto hormiga",
    "valor": ingresoParaLuegoGastar
  })
}
