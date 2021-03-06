package nl.atlasdev.v1wac.webservices;

import java.sql.SQLException;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import nl.atlasdev.v1wac.model.Country;
import nl.atlasdev.v1wac.model.ServiceProvider;
import nl.atlasdev.v1wac.model.WorldService;

@Path("/countries")
public class WorldResource {
	@GET
	@Produces("application/json")
	public String getCountries() throws SQLException {
		WorldService service = ServiceProvider.getWorldService();
		JsonArrayBuilder world = Json.createArrayBuilder();
		for (Country c : service.getAllCountries()) {
			world.add(this.buildCountry(c));
		}
		return world.build().toString();
	}

	@GET
	@Path("{code}")
	@Produces("application/json")
	public String getCountry(@PathParam("code") String code) throws SQLException {
		WorldService service = ServiceProvider.getWorldService();
		return buildCountry(service.getCountryByCode(code)).build().toString();
	}

	@DELETE
	@Path("{code}")
	@Produces("application/json")
	public String deleteCountry(@PathParam("code") String code) throws SQLException {
		WorldService service = ServiceProvider.getWorldService();
		return Json.createObjectBuilder().add("ok", service.deleteCountry(service.getCountryByCode(code))).build().toString();
	}

	@GET
	@Path("/largestsurfaces")
	@Produces("application/json")
	public String getSurfaceCountry() throws SQLException {
		WorldService service = ServiceProvider.getWorldService();
		JsonArrayBuilder world = Json.createArrayBuilder();
		for (Country c : service.get10LargestSurfaces()) {
			world.add(this.buildCountry(c));
		}
		return world.build().toString();
	}

	@GET
	@Path("/largestpopulations")
	@Produces("application/json")
	public String getPopulationCountry() throws SQLException {
		WorldService service = ServiceProvider.getWorldService();
		JsonArrayBuilder world = Json.createArrayBuilder();
		for (Country c : service.get10LargestPopulations()) {
			world.add(this.buildCountry(c));
		}
		return world.build().toString();
	}

	private JsonObjectBuilder buildCountry(Country c) {
		JsonObjectBuilder country = Json.createObjectBuilder();
		country.add("code", c.getCode());
		country.add("iso", c.getIso3Code());
		country.add("name", c.getName());
		country.add("continent", c.getContinent());
		country.add("capital", c.getCapital());
		country.add("region", c.getRegion());
		country.add("surface", c.getSurface());
		country.add("population", c.getPopulation());
		country.add("goverment", c.getGovernment());
		country.add("lat", c.getLatitude());
		country.add("lng", c.getLongitude());
		return country;
	}
}
