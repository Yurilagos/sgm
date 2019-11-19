package projeto.centroOperacoes.visao;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import projeto.centroOperacoes.controle.ControleNavio;

@ManagedBean
@ViewScoped
public class InfoWindowView implements Serializable {
      
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MapModel advancedModel;
  
    private Marker marker;
    
    ControleNavio cn = new ControleNavio();
  
    @PostConstruct
    public void init() {
        advancedModel = new DefaultMapModel();
          
        //Coordenadas para teste
        LatLng coord1 = new LatLng(-23.027827, -43.124010);
        LatLng coord2 = new LatLng(-22.932000, -43.138455);
        LatLng coord3 = new LatLng(-23.033403, -43.222654);
        LatLng coord4 = new LatLng(-22.855011, -43.156141);
        
        
          
        //Icones para teste
        advancedModel.addOverlay(new Marker(coord2, "Navio 2", "Objeto", corDaMarcacao(2)));
        advancedModel.addOverlay(new Marker(coord4, "Navio 3", "Objeto", corDaMarcacao(1)));
        advancedModel.addOverlay(new Marker(coord3, "Navio 4", "Objeto", corDaMarcacao(3)));
       // advancedModel.addOverlay(new Marker(coord1, cn.getNavio(1).getNome(), cn.getNavio(1), corDaMarcacao(cn.getNavio(1).getStatus())));
    }
  
    /**
     * Método para receber informações do GPS
     * @param lat
     * @param lng
     * @return
     */
    public LatLng recebeInfoGPSLatLng(Double lat, Double lng) {
    	LatLng posicao = new LatLng(lat, lng);
    	return posicao;
    }
    
    public MapModel getAdvancedModel() {
        return advancedModel;
    }
      
    public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();
    }
      
    public Marker getMarker() {
        return marker;
    }
    
    
    public String corDaMarcacao(Integer status) {
    	
    	switch (status) {
		case 0://Branco
			return "https://maps.google.com/mapfiles/ms/micons/white-dot.png";
		case 1://Verde
			return "https://maps.google.com/mapfiles/ms/micons/green-dot.png";
		case 2://Amarelo
			return "https://maps.google.com/mapfiles/ms/micons/yellow-dot.png";
		case 3://Vermelho
			return "https://maps.google.com/mapfiles/ms/micons/red-dot.png";
		

		default:
			return "";
		}
    	
    }
}