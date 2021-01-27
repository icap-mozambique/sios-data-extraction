/**
 * 
 */
package icap.columbia.org.mz.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Notification;
import org.zkoss.zul.A;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;

import icap.columbia.org.mz.mapper.Data;
import icap.columbia.org.mz.mapper.OrganizationUnity;
import icap.columbia.org.mz.service.util.Constants;
import icap.columbia.org.mz.service.util.PoiXLSUtil;
import icap.columbia.org.mz.service.util.SIOSUtil;

/**
 * @author Simone Fernando, 25/01/2021
 *
 */
@SuppressWarnings("rawtypes")
public class IndexController extends SelectorComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1994361683148539769L;

	@Wire
	A welcome, data, help, about;

	@Wire
	Div divWelcome, divData, divHelp, divAbout;

	@Wire
	Textbox username, password;

	@Wire
	Datebox startDate, endDate;

	@Wire
	Radiogroup level;

	@Wire
	Combobox cbxProvincia;

	@Wire
	Listbox lbxUnidadeSanitarias;

	@Wire
	Row rowUnidadeSanitaria;

	@Listen("onCreate = #fullPage")
	public void init() {
		divWelcome.setVisible(true);
		divData.setVisible(false);
		divHelp.setVisible(false);
		divAbout.setVisible(false);
	}

	@Listen("onClick = #welcome")
	public void showWelcome() {
		divWelcome.setVisible(true);
		divData.setVisible(false);
		divHelp.setVisible(false);
		divAbout.setVisible(false);
	}

	@Listen("onClick = #data")
	public void showWData() {
		divWelcome.setVisible(false);
		divData.setVisible(true);
		divHelp.setVisible(false);
		divAbout.setVisible(false);
	}

	@Listen("onClick = #help")
	public void showWHelp() {
		divWelcome.setVisible(false);
		divData.setVisible(false);
		divHelp.setVisible(true);
		divAbout.setVisible(false);
	}

	@Listen("onClick = #about")
	public void showAbout() {
		divWelcome.setVisible(false);
		divData.setVisible(false);
		divHelp.setVisible(false);
		divAbout.setVisible(true);
	}

	@SuppressWarnings("unchecked")
	@Listen("onSelect = #cbxProvincia")
	public void changeProvincia() {

		if (username.getValue() == null || username.getValue().isEmpty()) {
			throw new WrongValueException(username, "Campo inválido!");
		}

		if (password.getValue() == null | password.getValue().isEmpty()) {
			throw new WrongValueException(password, "Campo inválido!");
		}

		SIOSUtil.authenticate(username.getValue(), password.getValue());

		if (cbxProvincia.getSelectedItem() != null && level.getSelectedItem().getValue().equals("US")) {
			String uidProvincia = cbxProvincia.getSelectedItem().getValue();
			List<OrganizationUnity> unidades;
			if (uidProvincia.equals("ALL")) {
				unidades = SIOSUtil.getOrganisationUnityByLevel(Constants.SIOS_ORGANIZATION_LEVEL_ID_UNIDADE_SANITARIA);
			} else {

				unidades = SIOSUtil.getOrganisationUnityByIdAndLevel(uidProvincia,
						Constants.SIOS_ORGANIZATION_LEVEL_ID_PROVINCIA);
			}

			ListModelList<Object> listModel = new ListModelList(
					(unidades != null) ? unidades : new ArrayList<OrganizationUnity>());
			lbxUnidadeSanitarias.setModel(listModel);
			lbxUnidadeSanitarias.setMultiple(true);
			lbxUnidadeSanitarias.setCheckmark(true);
			lbxUnidadeSanitarias.invalidate();
		} else {
			rowUnidadeSanitaria.setVisible(false);
		}
	}

	@SuppressWarnings("unchecked")
	@Listen("onCheck = #level")
	public void changeLevel() {

		if (username.getValue() == null || username.getValue().isEmpty()) {
			throw new WrongValueException(username, "Campo inválido!");
		}

		if (password.getValue() == null | password.getValue().isEmpty()) {
			throw new WrongValueException(password, "Campo inválido!");
		}

		SIOSUtil.authenticate(username.getValue(), password.getValue());

		if (level.getSelectedItem().getValue().equals("US")) {
			if (cbxProvincia.getSelectedItem() != null) {
				rowUnidadeSanitaria.setVisible(true);
				String uidProvincia = cbxProvincia.getSelectedItem().getValue();
				List<OrganizationUnity> unidades;
				if (uidProvincia.equals("ALL")) {
					unidades = SIOSUtil
							.getOrganisationUnityByLevel(Constants.SIOS_ORGANIZATION_LEVEL_ID_UNIDADE_SANITARIA);
				} else {

					unidades = SIOSUtil.getOrganisationUnityByIdAndLevel(uidProvincia,
							Constants.SIOS_ORGANIZATION_LEVEL_ID_PROVINCIA);
				}

				ListModelList<Object> listModel = new ListModelList(
						(unidades != null) ? unidades : new ArrayList<OrganizationUnity>());
				lbxUnidadeSanitarias.setModel(listModel);
				lbxUnidadeSanitarias.setMultiple(true);
				lbxUnidadeSanitarias.setCheckmark(true);
				lbxUnidadeSanitarias.invalidate();
			} else {

				throw new WrongValueException(cbxProvincia, "Campo inválido!");
			}
		} else {
			rowUnidadeSanitaria.setVisible(false);
		}
	}

	@SuppressWarnings("unchecked")
	@Listen("onClick = #btnClear")
	public void clear() {
		username.setText("");
		username.setValue(null);
		password.setText("");
		password.setValue(null);
		cbxProvincia.setText("");
		cbxProvincia.setSelectedIndex(-1);
		cbxProvincia.setSelectedItem(null);
		ListModelList<Object> listModel = new ListModelList(new ArrayList<OrganizationUnity>());
		lbxUnidadeSanitarias.setModel(listModel);
		lbxUnidadeSanitarias.setMultiple(true);
		lbxUnidadeSanitarias.setCheckmark(true);
		rowUnidadeSanitaria.setVisible(false);

	}

	@SuppressWarnings("unchecked")
	@Listen("onClick = #btnGetdata")
	public void export() {
		if (validate()) {
			try {

				String start_Date = null;
				String end_Date = null;
				String filter = null;
				StringBuilder fileName = new StringBuilder("SIOS_DATA_EXTRACTION");

				SIOSUtil.authenticate(username.getValue(), password.getValue());

				String uidProvincia = cbxProvincia.getSelectedItem().getValue();

				String param_prv[] = null;
				if (uidProvincia.equals("ALL")) {
					fileName.append("_ALL");
					param_prv = new String[] { Constants.ID_OU_CABO_DELGADO, Constants.ID_OU_GAZA,
							Constants.ID_OU_MANICA, Constants.ID_OU_MAPUTO_CIDADE, Constants.ID_OU_MAPUTO_PROVINCIA,
							Constants.ID_OU_NAMPULA, Constants.ID_OU_NIASSA, Constants.ID_OU_SOFALA,
							Constants.ID_OU_TETE, Constants.ID_OU_ZAMBEZIA };
				} else if (!uidProvincia.equals("ALL") && level.getSelectedItem().getValue().equals("P")) {
					fileName.append("_BY_PRV");
					param_prv = new String[] { uidProvincia };
				} else if (!uidProvincia.equals("ALL") && level.getSelectedItem().getValue().equals("US")) {
					fileName.append("_BY_US");
					// pega todas as US da provincia seleccionadas
					List<OrganizationUnity> organizationUnities = new ArrayList(
							((ListModelList) lbxUnidadeSanitarias.getModel()).getSelection());
					if (organizationUnities.isEmpty()) {
						organizationUnities = (List<OrganizationUnity>) lbxUnidadeSanitarias.getModel();
					}

					List<String> ids = organizationUnities.stream()
							.map(organizationUnitie -> organizationUnitie.getId()).collect(Collectors.toList());
					param_prv = (String[]) ids.toArray();

				}

				if (startDate.getValue() != null) {
					start_Date = parse(startDate.getValue(), "yyyy-MM-dd");
					fileName.append("_" + start_Date);
				}

				if (endDate.getValue() != null) {
					end_Date = parse(endDate.getValue(), "yyyy-MM-dd");
					fileName.append("_" + end_Date);
				}

				// falta implementar os filtros

				Data data = SIOSUtil.getSIOSData(param_prv, Constants.ID_PROGRAM_FICHA_INTERNAMENTO_COVID_19,
						start_Date, end_Date, filter);

				PoiXLSUtil poiXLSUtil = new PoiXLSUtil();
				byte[] exported = poiXLSUtil.exportXLS(data, null);

				fileName.append(".xls");

				Notification.show("Operação executada com sucesso");

				Filedownload.save(exported, "application/vnd.ms-excel", fileName.toString());

			} catch (Exception e) {
				Messagebox.show("Ocorreu um erro tente novamente: " + e.getMessage());
			}
		}
	}

	private boolean validate() {

		if (username.getValue() == null || username.getValue().isEmpty()) {
			throw new WrongValueException(username, "Campo inválido!");
		}

		if (password.getValue() == null | password.getValue().isEmpty()) {
			throw new WrongValueException(password, "Campo inválido!");
		}

		if (cbxProvincia.getSelectedItem() == null) {
			throw new WrongValueException(cbxProvincia, "Campo inválido!");
		}
		return true;
	}

	private String parse(Date date, String pattern) {
		if (date != null) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String dateR = simpleDateFormat.format(date);
			return dateR;
		} else {
			return null;
		}
	}

}
