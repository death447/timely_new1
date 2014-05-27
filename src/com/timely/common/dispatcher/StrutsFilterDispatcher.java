package com.timely.common.dispatcher;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import org.apache.struts2.dispatcher.Dispatcher;
import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

/**
 * @category 系统平台初始化完成后, 初始化系统必须的启动程序
 * @author death447
 *
 */
public class StrutsFilterDispatcher extends StrutsPrepareAndExecuteFilter {
	private FilterConfig filterConfig;
	private Dispatcher dispatcher;

	@Override
	protected void postInit(Dispatcher dispatcher, FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
		this.dispatcher = dispatcher;
		super.postInit(dispatcher, filterConfig);
		//初始化相应信息
		initStartupInitable();
	}

	/** 初始化系统初始化时需要初始化的信息 */
	protected void initStartupInitable() {
		/**权限初始化,权限管理,负责管理权限的加载和删除*/
		//new PermissionRemoteIniter().init(filterConfig.getServletContext(), dispatcher);

		/**会员相关信息缓存*/
		//MemberCache.getInstance();
	}

	public void restart() throws ServletException {
		init(filterConfig);
	}
}
