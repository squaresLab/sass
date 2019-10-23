public class Plan1571770383675 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

} else {

}

StartServer("A");

StartServer("C");

DecreaseDimmer("A");


}

for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}


}
}
