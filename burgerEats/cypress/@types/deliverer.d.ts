interface IDeliverer {
	name: string;
	cpf: string;
	email: string;
	phone: string;
	vehicleType: string;
	cnhPicture: string; // search for file in fixtures
	address: {
		postalcode: string;
		street: string;
		number: string;
		district: string;
		cityAndState: string;
	};
}
