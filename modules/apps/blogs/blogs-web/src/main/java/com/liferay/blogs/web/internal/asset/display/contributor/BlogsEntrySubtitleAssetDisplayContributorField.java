package com.liferay.blogs.web.internal.asset.display.contributor;

import com.liferay.asset.display.contributor.AssetDisplayContributorField;
import com.liferay.blogs.model.BlogsEntry;
import com.liferay.portal.kernel.language.LanguageUtil;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

/**
 * @author Kyle Miho
 */
@Component(
  property = "model.class.name=com.liferay.blogs.model.BlogsEntry",
  service = AssetDisplayContributorField.class
)
public class BlogsEntrySubtitleAssetDisplayContributorField
  implements AssetDisplayContributorField<BlogsEntry> {

  @Override
  public String getKey() {
     return "subtitle";
  }

  @Override
  public String getLabel(Locale locale) {
     return LanguageUtil.get(locale, "subtitle");
  }

  @Override
  public String getType() {
     return "text";
  }

  @Override
  public String getValue(BlogsEntry blogsEntry, Locale locale) {
     return blogsEntry.getSubtitle();
  }

}
