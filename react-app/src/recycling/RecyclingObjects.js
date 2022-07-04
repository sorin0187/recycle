import { useEffect, useState } from "react";
import RecyclingObject from './RecyclingObject';

function RecyclingObjects() {
  const [recyclingCans, setRecyclingCans] = useState([]);
  const [recyclingBottles, setRecyclingBottles] = useState([]);
  const [vouchers, setVouchers] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const allPaid = recyclingCans.map(can => can.paid).reduce((previous, current) => previous && current, true)
                && recyclingBottles.map(bottle => bottle.paid).reduce((previous, current) => previous && current, true);

  const recycleCan = () => {
    setIsLoading(true);
    fetch("http://localhost:8080/can", {
      method: 'POST',
      headers: new Headers({
          'Accept': 'application/json',
          'Content-Type': 'application/json'
      }),
      body: null
      })
      .then(response => response.json())
      .then(data => setRecyclingCans([data, ...recyclingCans]))
      .then(() => setIsLoading(false));
  }

  const recycleBottle = () => {
    setIsLoading(true);
    fetch("http://localhost:8080/bottle", {
      method: 'POST',
      headers: new Headers({
          'Accept': 'application/json',
          'Content-Type': 'application/json'
      }),
      body: null
      })
      .then(response => response.json())
      .then(data => setRecyclingBottles([data, ...recyclingBottles]))
      .then(() => setIsLoading(false));
  }

  const fetchCans = () => {
    fetch("http://localhost:8080/can?paid=false", {
       method: 'GET',
       headers: new Headers({
            'Accept': 'application/json',
            'Content-Type': 'application/json'
       })
   })
   .then(response => response.json())
   .then(data => setRecyclingCans(data));
  }

  const fetchBottles = () => {
    fetch("http://localhost:8080/bottle?paid=false", {
       method: 'GET',
       headers: new Headers({
            'Accept': 'application/json',
            'Content-Type': 'application/json'
       })
   })
   .then(response => response.json())
   .then(data => setRecyclingBottles(data))
  }

  const fetchVouchers = () => {
    fetch("http://localhost:8080/voucher", {
       method: 'GET',
       headers: new Headers({
            'Accept': 'application/json',
            'Content-Type': 'application/json'
       })
   })
   .then(response => response.json())
   .then(data => setVouchers(data))
  }

  const generateVoucher = () => {
    fetch("http://localhost:8080/voucher", {
      method: 'POST',
      headers: new Headers({
          'Accept': 'application/json',
          'Content-Type': 'application/json'
      }),
      body: null
      })
      .then(response => response.json())
      .then(data => setVouchers([data, ...vouchers]))
      .then(() => {
        setIsLoading(false);
        fetchCans();
        fetchBottles();
      });
  }

  useEffect(() => {
    fetchCans();
    fetchBottles();
    fetchVouchers();
  }, [])

  return (
    <div className="recyclingObject">
      <button 
          type="button" 
          className="recycle-button"  
          onClick={recycleCan}
          disabled={isLoading}>
            Recycle Can
      </button>
      <button 
          type="button" 
          className="recycle-button"  
          onClick={recycleBottle}
          disabled={isLoading}>
            Recycle Bottle
      </button>
      <button 
          type="button" 
          className="recycle-button"  
          onClick={generateVoucher}
          disabled={isLoading || allPaid}>
            Create voucher
      </button>
      <h3 className="recyclingObject-title">Cans</h3>
      <div className="recyclingObject-constainer">
        {
          recyclingCans.map(can => (
            <RecyclingObject key={can.id} recyclingObject={can} type='can' />
          )
        )}
      </div>
      <h3 className="recyclingObject-title">Bottles</h3>
      <div className="recyclingObject-constainer">
        {
          recyclingBottles.map(bottle => (
            <RecyclingObject key={bottle.id} recyclingObject={bottle} type='bottle' />
          )
        )}
      </div>
      <h3 className="recyclingObject-title">Vouchers</h3>
      <div className="vouchers-constainer">
        {
          vouchers.map(voucher => (
            <div key={voucher.id}>{voucher.code + " -> NOK " + voucher.value}</div>
          )
        )}
      </div>
    </div>
  );
}

export default RecyclingObjects;
