function RecyclingObject({recyclingObject, type}) {
  return (
    <div className="recycling-object">
      <div className="recycling-object-icon-container">
        <img alt={"" + recyclingObject.paid} className={`recycling-object-icon ${recyclingObject.paid ? 'paid' : 'unpaid'}`} 
          src={"/" + type + ".png"} />
      </div>
      <div className="recycling-object-price-container">
        {recyclingObject.price}
      </div>
      <div className="recycling-object-inserted-at">
        {recyclingObject.insertedAt}
      </div>
    </div>
  );
}

export default RecyclingObject;
