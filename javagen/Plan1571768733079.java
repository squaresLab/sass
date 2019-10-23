public class Plan1571768733079 extends Plan { 
public static void main(String[] args) { 
DecreaseTraffic("A");
StartServer("A");

for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
StartServer("B");
DecreaseTraffic("A");

} else {
DecreaseDimmer("A");
}

StartServer("B");

}


}
}
