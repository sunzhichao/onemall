package cn.sunzhichao.mall.controller.backend;

import cn.sunzhichao.mall.common.Const;
import cn.sunzhichao.mall.common.ResponseCode;
import cn.sunzhichao.mall.common.ServerResponse;
import cn.sunzhichao.mall.pojo.User;
import cn.sunzhichao.mall.service.IOrderService;
import cn.sunzhichao.mall.service.IUserService;
import cn.sunzhichao.mall.vo.OrderVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/manage/order/", method = RequestMethod.POST)
public class OrderManageController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IOrderService iOrderService;

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo> orderList(HttpSession session, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                              @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.NEED_LOGIN.getCode(),
                    "用户未登录，请登录管理员"
            );
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //通过校验,填充业务逻辑
            return iOrderService.manageList(pageNum, pageSize);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse<OrderVo> orderDetail(HttpSession session, Long orderNo) {

        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.NEED_LOGIN.getCode(),
                    "用户未登录，请登录管理员"
            );
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //通过校验,填充业务逻辑
            return iOrderService.manageDetail(orderNo);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    //按照订单号搜索
    @RequestMapping("search.do")
    @ResponseBody
    public ServerResponse<PageInfo> orderSearch(HttpSession session, Long orderNo,
                                               @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.NEED_LOGIN.getCode(),
                    "用户未登录，请登录管理员"
            );
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //通过校验,填充业务逻辑
            return iOrderService.manageSearch(orderNo, pageNum, pageSize);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    @RequestMapping("send_goods.do")
    @ResponseBody
    public ServerResponse<String> orderSendGoods(HttpSession session, Long orderNo) {

        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.NEED_LOGIN.getCode(),
                    "用户未登录，请登录管理员"
            );
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //通过校验,填充业务逻辑
            return iOrderService.manageSendGoods(orderNo);
         } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }
}
