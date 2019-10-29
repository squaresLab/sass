public class Plan1571771856326 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseDimmer("C") ) {
DecreaseTraffic("A");
} else {
if ( StartServer("C") ) {
DecreaseDimmer("A");
IncreaseTraffic("A");

for (int i = 0; i < 7 ; i++) {
StartServer("A");
}


} else {
IncreaseDimmer("A");
}

StartServer("A");

DecreaseTraffic("B");

}

DecreaseDimmer("C");
DecreaseDimmer("C");
StartServer("A");
for (int i = 0; i < 4 ; i++) {
StartServer("A");
}





}
}
