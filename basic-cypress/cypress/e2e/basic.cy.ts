describe("Basic testing ", ()=>{
   it("Check if its true", () => {
      expect(2).to.eq(2);
   })

   it("Should fail ",()=>{
      expect(2).not.to.eq(5);
   })
});