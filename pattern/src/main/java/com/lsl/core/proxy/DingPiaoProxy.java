package com.lsl.core.proxy;

/**
 * @Author: liushoulong
 * @Date: 2019/11/3 14:47
 *
 * 购票代理，它只有一个权限，那就是只能售票，不具有推销的权限，所以，代购点不能拥有火车站的所有权限，
 * 这时就需要给代购点提供代理接口
 */
public class DingPiaoProxy {

    private DingPiaoService dingPiaoService;

    public DingPiaoProxy(){
       dingPiaoService = new DingPiaoServiceImp();
    }

    /**
     * 代理购票
     * @return
     */
    public int ticketProxy() throws Exception{
        before();
        int code = dingPiaoService.ticket("","");
        after();
       return code;
    }


    public void before(){
        System.out.println("购票开始前....");
    }

    public void after(){
        System.out.println("购票结束后....");
    }
}
