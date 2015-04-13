package com.vlives.site.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vlives.site.entity.CityObject;
import com.vlives.site.util.CityHelper;

public class CityFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		// check request url
		String uri = httpRequest.getRequestURI();
		if (uri != null) {
			if (uri.startsWith("/index")
					|| uri.startsWith("/search")
					|| uri.startsWith("/merchant")
					|| uri.startsWith("/promotion")
					|| uri.startsWith("/promotions")
					|| uri.startsWith("user")) {
				if (!uri.contains("ajax")) {
					
				}
			}
		}
		
		CityObject currentCity = CityHelper.getCurrentCity();
		httpRequest.setAttribute("cities", getCities(currentCity.getCity()
				.getId()));
		httpRequest.setAttribute("currentCity", currentCity);

		chain.doFilter(httpRequest, httpResponse);
		return;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	/**
	 * 网站支持城市列表
	 * 
	 * @return
	 */
	private List<Map.Entry<String, List<CityObject>>> getCities(
			long currentCityId) {
		// TODO a better solution to setup which cities are available/opening

		// Group cities by first letter of city PinYin name
		Map<String, List<CityObject>> map = new HashMap<String, List<CityObject>>();
		for (CityObject city : CityHelper.CITIES) {
			if (city.getCity().getId() == currentCityId) {
				city.setCurrent(true);
			} else {
				city.setCurrent(false);
			}

			String cityLetter = city.getNameInPinYin().substring(0, 1)
					.toUpperCase();
			if (map.containsKey(cityLetter)) {
				List<CityObject> list = map.get(cityLetter);
				list.add(city);
			} else {
				List<CityObject> list = new ArrayList<CityObject>();
				list.add(city);
				map.put(cityLetter, list);
			}
		}
		// Sort cities by first letter of city PinYin name
		List<Map.Entry<String, List<CityObject>>> entries = new ArrayList<Map.Entry<String, List<CityObject>>>(
				map.entrySet());
		Collections.sort(entries,
				new Comparator<Map.Entry<String, List<CityObject>>>() {
					@Override
					public int compare(Entry<String, List<CityObject>> arg0,
							Entry<String, List<CityObject>> arg1) {
						return arg0.getKey().compareTo(arg1.getKey());
					}
				});

		return entries;
	}
}
