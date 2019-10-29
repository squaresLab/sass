public class Plan1571772827771 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
for (int i = 0; i < 2 ; i++) {
StartServer("A");
}

}

if ( IncreaseTraffic("A") ) {
StartServer("A");
} else {
for (int i = 0; i < 2 ; i++) {
DecreaseTraffic("A");
}

}

DecreaseDimmer("C");


for (int i = 0; i < 4 ; i++) {
StartServer("C");
StartServer("B");

}


}
}
