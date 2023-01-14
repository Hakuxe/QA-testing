import { faker } from "@faker-js/faker";
import { generate } from 'gerador-validador-cpf'

export default function createRandomDeliverer(): IDeliverer {
	const firstName = faker.name.firstName();
	const lastName = faker.name.lastName();

	let data = {
		name: `${firstName} ${lastName}`,
		cpf: generate(),
		email: faker.internet.email(firstName),
		phone: faker.phone.number('## 9########'),
		vehicleType: "moto",
		cnhPicture: "images/cnh-digital.jpg",
		address: {
			postalcode: "04534011",
			street: "Joaquim Floriano",
			number: "1000",
			district: "Itaim Bibi",
			cityAndState: "São Paulo/SP",
		},
	};

	return data;
}
