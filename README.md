# JADE Book Trading

This project represents the "Book Trading" example implementation from [JADE Programming Tutorial for Beginners](https://jade.tilab.com/doc/tutorials/JADEProgramming-Tutorial-for-beginners.pdf).

## Prerequisites

- macOS 11.6 or higher
- Java 17 or higher
- JADE 4.6.0

## Running

To run the "Book Trading" app that launches two JADE agents: buyer and seller, open macOS **Terminal** and execute the following command line:

```
chmod a+x run.sh
./run.sh
```

## Output
 ```
eugenegoncharov@MacBook-Pro-2 mstt_labs % ./run.sh                                                          
added manifest
adding: ua/nure/mstt_labs/(in = 0) (out= 0)(stored 0%)
adding: ua/nure/mstt_labs/BookBuyerAgent$1.class(in = 1825) (out= 919)(deflated 49%)
adding: ua/nure/mstt_labs/BookBuyerAgent.class(in = 1896) (out= 1004)(deflated 47%)
adding: ua/nure/mstt_labs/BookSellerAgent$1.class(in = 923) (out= 530)(deflated 42%)
adding: ua/nure/mstt_labs/BookSellerAgent.class(in = 2898) (out= 1402)(deflated 51%)
adding: ua/nure/mstt_labs/Buyer.class(in = 156) (out= 144)(deflated 7%)
adding: ua/nure/mstt_labs/Seller.class(in = 245) (out= 183)(deflated 25%)
adding: ua/nure/mstt_labs/behaviours/(in = 0) (out= 0)(stored 0%)
adding: ua/nure/mstt_labs/behaviours/OfferRequestsServer.class(in = 1419) (out= 807)(deflated 43%)
adding: ua/nure/mstt_labs/behaviours/PurchaseOrdersServer.class(in = 2028) (out= 1075)(deflated 46%)
adding: ua/nure/mstt_labs/behaviours/RequestPerformer.class(in = 3551) (out= 1962)(deflated 44%)
adding: ua/nure/mstt_labs/ui/(in = 0) (out= 0)(stored 0%)
adding: ua/nure/mstt_labs/ui/BookSellerGui$1.class(in = 1861) (out= 948)(deflated 49%)
adding: ua/nure/mstt_labs/ui/BookSellerGui$2.class(in = 733) (out= 409)(deflated 44%)
adding: ua/nure/mstt_labs/ui/BookSellerGui.class(in = 2139) (out= 1195)(deflated 44%)
Jan 23, 2023 9:07:28 PM jade.core.Runtime beginContainer
INFO: ----------------------------------
    This is JADE 4.6.0 - revision 6869 of 30-11-2022 14:47:03
    downloaded in Open Source, under LGPL restrictions,
    at http://jade.tilab.com/
----------------------------------------
Jan 23, 2023 9:07:28 PM jade.imtp.leap.LEAPIMTPManager initialize
INFO: Listening for intra-platform commands on address:
- jicp://192.168.0.86:1099

Jan 23, 2023 9:07:28 PM jade.core.BaseService init
INFO: Service jade.core.management.AgentManagement initialized
Jan 23, 2023 9:07:28 PM jade.core.BaseService init
INFO: Service jade.core.messaging.Messaging initialized
Jan 23, 2023 9:07:28 PM jade.core.BaseService init
INFO: Service jade.core.resource.ResourceManagement initialized
Jan 23, 2023 9:07:28 PM jade.core.BaseService init
INFO: Service jade.core.mobility.AgentMobility initialized
Jan 23, 2023 9:07:28 PM jade.core.BaseService init
INFO: Service jade.core.event.Notification initialized
Jan 23, 2023 9:07:28 PM jade.mtp.http.HTTPServer <init>
INFO: HTTP-MTP Using XML parser com.sun.org.apache.xerces.internal.jaxp.SAXParserImpl$JAXPSAXParser
Jan 23, 2023 9:07:28 PM jade.core.messaging.MessagingService boot
INFO: MTP addresses:
http://localhost:7778/acc
Jan 23, 2023 9:07:28 PM jade.core.AgentContainerImpl joinPlatform
INFO: --------------------------------------
Agent container Main-Container@192.168.0.86 is ready.
--------------------------------------------
Hello! Buyer-agent buyer@192.168.0.86:1099/JADE is ready.
Trying to buy Prisoner of Azkaban

Prisoner of Azkaban sold to agent buyer@192.168.0.86:1099/JADE
Prisoner of Azkaban successfully purchased from agent seller@192.168.0.86:1099/JADE
Price = 100
Buyer-agent buyer@192.168.0.86:1099/JADE terminating.
```