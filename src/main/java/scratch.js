fetch('https://www.example.com/data')
  .then(response => response.json())
  .then(data => {
    let S = data.S;
    let K = data.K;
    let r = data.r;
    let v = data.v;
    let T = data.T;

    // Pass the retrieved data to the call_price and put_price methods
    let call = BlackScholes.call_price(S, K, r, v, T);
    let put = BlackScholes.put_price(S, K, r, v, T);

    console.log("Call Price:      " + call);
    console.log("Put Price:       " + put);
  })
  .catch(error => {
    console.error('There was a problem with the fetch operation:', error);
  });
