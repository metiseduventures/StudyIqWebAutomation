package apiUtil;

import pojo.courseView.CourseView;
import util.APIResponse;
import util.APIUtils;
import util.ConfigFileReader;

public class CourseApiUtil {

	public ConfigFileReader cfReaderObject = new ConfigFileReader();
	public APIUtils apiUtilsObj = new APIUtils();
	APIResponse ap = null;

	public CourseView getCourseViewData(String strCourseSlug) {
		CourseView courseViewObj = null;
		try {

			ap = apiUtilsObj.getCall(cfReaderObject.getBaseUrl(), "web/courseview/" + strCourseSlug, null);
			if (ap.code != 200) {
				return null;
			}

			courseViewObj = ap.responseData.as(CourseView.class);

		} catch (Exception e) {
			// TODO: handle exception
		}

		return courseViewObj;

	}
}
